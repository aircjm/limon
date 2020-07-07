package com.aircjm.project.card.service.impl;

import com.aircjm.common.utils.LocalDateUtils;
import com.aircjm.common.utils.bean.BeanUtils;
import com.aircjm.common.utils.poi.ExcelUtil;
import com.aircjm.framework.web.domain.AjaxResult;
import com.aircjm.project.anki.response.AnkiRespVo;
import com.aircjm.project.card.domain.Cell;
import com.aircjm.project.card.mapper.CellCardMapper;
import com.aircjm.project.card.service.AnkiService;
import com.aircjm.project.card.service.CellCardService;
import com.aircjm.project.card.vo.anki.vo.Fields;
import com.aircjm.project.card.vo.anki.vo.Note;
import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import com.aircjm.project.card.vo.request.SetAnkiRequest;
import com.aircjm.project.card.vo.response.CellCardDetailResponse;
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
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 卡片服务
 *
 * @author aircjm
 */
@Service
@Slf4j
public class CellCardServiceImpl extends ServiceImpl<CellCardMapper, Cell> implements CellCardService {

    @Resource
    Trello trello;

    @Resource
    private ISysConfigService configService;

    @Override
    public void saveCard(SaveCardRequest request) {
    }

    @Override
    public Page<CellCardDetailResponse> getCardList(GetCardRequest request) {
        QueryWrapper<Cell> queryWrapper = new QueryWrapper<>();
        Page<Cell> boardCards = page(request, queryWrapper);
        Page<CellCardDetailResponse> response = new Page<>();
        BeanUtils.copyProperties(boardCards, response);
        response.setRecords(boardCards.getRecords().stream().map(item -> {
            CellCardDetailResponse detail = new CellCardDetailResponse();
            BeanUtils.copyProperties(item, detail);
            return detail;
        }).collect(Collectors.toList()));
        return response;
    }

    @Override
    public AjaxResult exportCard(GetCardRequest request) {
        Page<CellCardDetailResponse> page = getCardList(request);
        ExcelUtil<CellCardDetailResponse> util = new ExcelUtil<>(CellCardDetailResponse.class);
        AjaxResult ajaxResult = util.exportExcel(page.getRecords(), "卡片集合");

        return ajaxResult;
    }

    @Override
    public void updateAllCard() {
        String memberId = configService.selectConfigByKey("trello.default.memberId");
        Member member = trello.getMemberInformation(memberId);
        List<Board> boards = member.getBoards();
        log.info("获取所有board 开始更新");

        boards.forEach(board -> {
            List<Card> cardList = trello.getBoardCards(board.getId());
            QueryWrapper<Cell> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("board_id", board.getId());
            Map<String, Cell> cellCardMap = list(queryWrapper).stream().collect(Collectors.toMap(Cell::getCardId, Function.identity()));

            cardList.stream().forEach(card -> {
                        Cell cell = cellCardMap.get(card.getId());
                        if (Objects.isNull(cell)) {
                            cell = Cell.builder().boardId(board.getId())
                                    .cardId(Optional.ofNullable(card.getId()).orElse(""))
                                    .cardTitle(Optional.ofNullable(card.getName()).orElse(""))
                                    .cardDesc(Optional.ofNullable(card.getDesc()).orElse(""))
                                    .listId(Optional.ofNullable(card.getIdList()).orElse(""))
                                    .trelloUpdateTime(LocalDateUtils.date2LocalDate(card.getDateLastActivity()))
                                    .build();
                        } else {
                            if (LocalDateUtils.date2LocalDate(card.getDateLastActivity()).isAfter(cell.getTrelloUpdateTime())) {
                                cell.setCardTitle(Optional.ofNullable(card.getName()).orElse(""));
                                cell.setCardDesc(Optional.ofNullable(card.getDesc()).orElse(""));
                                cell.setBoardId(Optional.ofNullable(card.getIdBoard()).orElse(""));
                                cell.setTrelloUpdateTime(LocalDateUtils.date2LocalDate(card.getDateLastActivity()));
                            } else {
                                return;
                            }
                        }

                        UpdateWrapper<Cell> tWrapper = new UpdateWrapper<>();
                        tWrapper.eq("card_id", card.getId());
                        saveOrUpdate(cell, tWrapper);
                    }
            );

        });

    }

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private AnkiService ankiService;


    @Override
    public void setAnki(SetAnkiRequest request) {
        QueryWrapper<Cell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_id", request.getCardId());
        Cell one = getOne(queryWrapper);
        log.info("获取的卡片为：{}", one.getCardTitle());

        // 开始生成卡片
        Card card = trello.getCard("5ef19b4c4afff2868182d957");
        Note note = convert(card);
        log.info("请求生成anki卡片请求参数是：{}", JSON.toJSONString(note));
        log.info("请求生成anki desc：{}", card.getDesc());
         AnkiRespVo ankiRespVo = ankiService.addNote(note);
        if (Objects.nonNull(ankiRespVo)) {
            log.info("生产卡片成功 开始保存数据， {}", JSON.toJSONString(ankiRespVo));
        }
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
