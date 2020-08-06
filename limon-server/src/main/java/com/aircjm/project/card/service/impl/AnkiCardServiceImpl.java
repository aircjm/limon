package com.aircjm.project.card.service.impl;

import com.aircjm.common.exception.CustomException;
import com.aircjm.common.utils.StringUtils;
import com.aircjm.common.utils.bean.BeanUtils;
import com.aircjm.common.utils.poi.ExcelUtil;
import com.aircjm.framework.web.domain.AjaxResult;
import com.aircjm.project.anki.response.AnkiRespVo;
import com.aircjm.project.card.domain.AnkiCard;
import com.aircjm.project.card.domain.TrelloCard;
import com.aircjm.project.card.mapper.CellCardMapper;
import com.aircjm.project.card.service.AnkiCardService;
import com.aircjm.project.card.service.AnkiService;
import com.aircjm.project.card.vo.anki.vo.Fields;
import com.aircjm.project.card.vo.anki.vo.Note;
import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import com.aircjm.project.card.vo.request.SetAnkiRequest;
import com.aircjm.project.card.vo.response.AnkiCardDetailResponse;
import com.aircjm.project.system.service.ISysConfigService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 卡片服务
 *
 * @author aircjm
 */
@Service
@Slf4j
public class AnkiCardServiceImpl extends ServiceImpl<CellCardMapper, AnkiCard> implements AnkiCardService {

    @Resource
    Trello trello;



    @Resource
    private AnkiService ankiService;


    @Resource(name = "asyncExecutor")
    private Executor asyncExecutor;


    @Override
    public void saveCard(SaveCardRequest request) {
    }

    @Override
    public Page<AnkiCardDetailResponse> getAnkiCardList(GetCardRequest request) {
        QueryWrapper<AnkiCard> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(request.getStatus())) {
            queryWrapper.eq("status", request.getStatus());
        }
        if (Objects.nonNull(request.getAnkiStatus())) {
            if (request.getAnkiStatus().equals(1)) {
                queryWrapper.isNotNull("anki_note_id");
            } else if (request.getAnkiStatus().equals(0)) {
                queryWrapper.isNull("anki_note_id");
            }
        }

        Page<AnkiCard> boardCards = page(request, queryWrapper);
        Page<AnkiCardDetailResponse> response = new Page<>();
        BeanUtils.copyProperties(boardCards, response);
        response.setRecords(boardCards.getRecords().stream().map(item -> {
            AnkiCardDetailResponse detail = new AnkiCardDetailResponse();
            BeanUtils.copyProperties(item, detail);
            return detail;
        }).collect(Collectors.toList()));
        return response;
    }

    @Override
    public AjaxResult exportCard(GetCardRequest request) {
        Page<AnkiCardDetailResponse> page = getAnkiCardList(request);
        ExcelUtil<AnkiCardDetailResponse> util = new ExcelUtil<>(AnkiCardDetailResponse.class);

        return util.exportExcel(page.getRecords(), "卡片集合");
    }

    @Override
    public void updateAllCard() {
        String memberId = configService.selectConfigByKey("trello.default.memberId");
        Member member = trello.getMemberInformation(memberId);
        List<Board> boards = member.getBoards();
        log.info("获取所有board 开始更新");

        boards.forEach(board -> {
            List<Card> cardList = trello.getBoardCards(board.getId());
            QueryWrapper<AnkiCard> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("board_id", board.getId());
            Map<String, AnkiCard> cellCardMap = list(queryWrapper).stream().collect(Collectors.toMap(AnkiCard::getCardId, Function.identity()));

            cardList.forEach(card -> {
                        AnkiCard ankiCard = cellCardMap.get(card.getId());
                        if (Objects.isNull(ankiCard)) {
                            ankiCard = AnkiCard.builder().boardId(board.getId())
                                    .cardId(Optional.ofNullable(card.getId()).orElse(""))
                                    .cardTitle(Optional.ofNullable(card.getName()).orElse(""))
                                    .cardDesc(Optional.ofNullable(card.getDesc()).orElse(""))
                                    .listId(Optional.ofNullable(card.getIdList()).orElse(""))
                                    .trelloUpdateTime(LocalDateUtils.date2LocalDate(card.getDateLastActivity()))
                                    .build();
                            ankiCard.setCreateTime(LocalDateTime.now());
                            save(ankiCard);
                        } else {
                            if (LocalDateUtils.date2LocalDate(card.getDateLastActivity()).isAfter(ankiCard.getTrelloUpdateTime())) {
                                ankiCard.setCardTitle(Optional.ofNullable(card.getName()).orElse(""));
                                ankiCard.setCardDesc(Optional.ofNullable(card.getDesc()).orElse(""));
                                ankiCard.setBoardId(Optional.ofNullable(card.getIdBoard()).orElse(""));
                                ankiCard.setTrelloUpdateTime(LocalDateUtils.date2LocalDate(card.getDateLastActivity()));

                                UpdateWrapper<AnkiCard> tWrapper = new UpdateWrapper<>();
                                tWrapper.eq("card_id", card.getId());
                                update(ankiCard, tWrapper);
                            } else {
                                log.info("card : {} 当前数据为最新的无需更新", card.getId());
                            }
                        }

                    }
            );

        });

    }

    @Override
    public void asyncUpdateAllCard() {
        asyncExecutor.execute(this::updateAllCard);
    }


    @Override
    public void setAnki(SetAnkiRequest request) {
        QueryWrapper<AnkiCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_id", request.getCardId());
        AnkiCard one = getOne(queryWrapper);
        log.info("获取的卡片为：{}", one.getCardTitle());

        // 开始生成卡片
        Card card = trello.getCard(request.getCardId());
        Note note = convert(card);
        log.info("请求生成anki卡片请求参数是：{}", JSON.toJSONString(note));
        AnkiRespVo ankiRespVo = ankiService.addNote(note);
        if (StringUtils.isNotEmpty(ankiRespVo.getError())) {
            throw new CustomException("生成Anki的Note失败，失败原因为：" + ankiRespVo.getError());
        } else{
            one.setAnkiNoteId(ankiRespVo.getResult());
            one.setAnkiNoteUpdateTime(LocalDateTime.now());
        }
        updateById(one);
    }


    private static Note convert(com.julienvey.trello.domain.Card card) {
        Note note = new Note();
        note.setDeckName("trello");
        note.setModelName("Markdown");

        Fields fields = new Fields();
        fields.setFront(card.getName());
        fields.setBack(card.getDesc().replace("\n", "<br>"));
        note.setFields(fields);
        if (CollectionUtils.isNotEmpty(card.getLabels())) {
            note.setTags(card.getLabels().stream().map(Label::getName).collect(Collectors.toList()));
        }

        return note;
    }

}
