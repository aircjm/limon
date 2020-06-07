package com.aircjm.project.card.service.impl;

import com.aircjm.project.card.domain.CellCard;
import com.aircjm.project.card.mapper.CellCardMapper;
import com.aircjm.project.card.service.CellCardService;
import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import com.aircjm.project.system.service.ISysConfigService;
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
    public Page<Card> getCardList(GetCardRequest request) {
        Page<Card> page = new Page<>();
        Board board = trello.getBoard(configService.selectConfigByKey("trello.default.boardId"));
        List<Card> boardCards = trello.getBoardCards(board.getId());
        page.setRecords(boardCards);
        page.setCurrent(1);
        page.setTotal(boardCards.size());
        page.setSize(1000);
        return page;
    }

    @Override
    public void updateAllCard() {
        String memberId = configService.selectConfigByKey("trello.default.memberId");
        Member member = trello.getMemberInformation(memberId);
        List<Board> boards = member.getBoards();
        log.info("获取所有board 开始更新");

        boards.forEach(board -> {
            List<Card> cardList = trello.getBoardCards(board.getId());
            List<CellCard> cellCardList = cardList.stream().map(card -> CellCard.builder().boardId(board.getId())
                    .cardId(card.getId())
                    .title(card.getName())
                    .desc(card.getDesc())
                    .listId(card.getIdList())
                    .build()).collect(Collectors.toList());
            saveOrUpdateBatch(cellCardList);

        });

    }

}
