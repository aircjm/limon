package com.aircjm.project.card.service.impl;

import com.aircjm.common.utils.LocalDateUtils;
import com.aircjm.common.utils.bean.BeanUtils;
import com.aircjm.project.card.domain.CellCard;
import com.aircjm.project.card.mapper.CellCardMapper;
import com.aircjm.project.card.service.CellCardService;
import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import com.aircjm.project.card.vo.response.CellCardDetailResponse;
import com.aircjm.project.system.service.ISysConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 卡片服务
 *
 * @author aircjm
 */
@Service
@Slf4j
public class CellCardServiceImpl extends ServiceImpl<CellCardMapper, CellCard> implements CellCardService {

    @Resource
    Trello trello;

    @Resource
    private ISysConfigService configService;

    @Override
    public void saveCard(SaveCardRequest request) {
    }

    @Override
    public Page<CellCardDetailResponse> getCardList(GetCardRequest request) {
        QueryWrapper<CellCard> queryWrapper = new QueryWrapper<>();
        Page<CellCard> boardCards = page(request,queryWrapper);
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
    public void updateAllCard() {
        String memberId = configService.selectConfigByKey("trello.default.memberId");
        Member member = trello.getMemberInformation(memberId);
        List<Board> boards = member.getBoards();
        log.info("获取所有board 开始更新");

        boards.forEach(board -> {
            List<Card> cardList = trello.getBoardCards(board.getId());
            QueryWrapper<CellCard> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("board_id", board.getId());
            Map<String, CellCard> cellCardMap = list(queryWrapper).stream().collect(Collectors.toMap(CellCard::getCardId, Function.identity()));


            cardList.stream().forEach(card -> {
                        CellCard cellCard = cellCardMap.get(card.getId());
                        if (Objects.isNull(cellCard)) {
                            cellCard = CellCard.builder().boardId(board.getId())
                                    .cardId(Optional.ofNullable(card.getId()).orElse(""))
                                    .cardTitle(Optional.ofNullable(card.getName()).orElse(""))
                                    .cardDesc(Optional.ofNullable(card.getDesc()).orElse(""))
                                    .listId(Optional.ofNullable(card.getIdList()).orElse(""))
                                    .trelloUpdateTime(LocalDateUtils.date2LocalDate(card.getDateLastActivity()))
                                    .build();
                        } else {
                            if (LocalDateUtils.date2LocalDate(card.getDateLastActivity()).isAfter(cellCard.getTrelloUpdateTime())) {
                                cellCard.setCardTitle(Optional.ofNullable(card.getName()).orElse(""));
                                cellCard.setCardDesc(Optional.ofNullable(card.getDesc()).orElse(""));
                                cellCard.setBoardId(Optional.ofNullable(card.getIdBoard()).orElse(""));
                                cellCard.setTrelloUpdateTime(LocalDateUtils.date2LocalDate(card.getDateLastActivity()));
                            } else {
                                return;
                            }
                        }

                        UpdateWrapper<CellCard> tWrapper = new UpdateWrapper<>();
                        tWrapper.eq("card_id", card.getId());
                        saveOrUpdate(cellCard, tWrapper);
                    }
            );

        });

    }

}
