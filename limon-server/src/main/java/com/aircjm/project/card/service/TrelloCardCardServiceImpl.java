package com.aircjm.project.card.service;

import com.aircjm.project.card.domain.TrelloCard;
import com.aircjm.project.card.domain.Webhook;
import com.aircjm.project.card.mapper.TrelloCardMapper;
import com.aircjm.project.system.service.ISysConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TrelloCardCardServiceImpl extends ServiceImpl<TrelloCardMapper, TrelloCard> implements TrelloCardService {

    @Resource
    private ISysConfigService configService;

    @Resource
    Trello trello;

    @Override
    public Webhook createWebhook(String description, String callbackUrl, String idModel) {
        return null;
    }

    @Override
    public void deleteWebhook(String idWebhook) {

    }

    @Override
    public Webhook getWebhook(String idWebhook) {
        return null;
    }


    @Override
    public void updateAllCard() {
        String memberId = configService.selectConfigByKey("trello.default.memberId");
        Member member = trello.getMemberInformation(memberId);
        List<Board> boards = member.getBoards();
        log.info("获取所有board 开始更新");

        boards.forEach(board -> {
            List<Card> cardList = trello.getBoardCards(board.getId());
            QueryWrapper<TrelloCard> queryWrapper = new QueryWrapper<>();


            cardList.forEach(card -> {
                        queryWrapper.eq("card_id", board.getId());
                        TrelloCard trelloCard = getOne(queryWrapper);
                        if (Objects.isNull(trelloCard)) {
                            trelloCard = TrelloCard.builder().boardId(board.getId())
                                    .cardId(Optional.ofNullable(card.getId()).orElse(""))
                                    .cardTitle(Optional.ofNullable(card.getName()).orElse(""))
                                    .cardDesc(Optional.ofNullable(card.getDesc()).orElse(""))
                                    .card(card)
                                    .dateLastActivity(card.getDateLastActivity())
                                    .build();
                        } else {
                            if (card.getDateLastActivity().after(trelloCard.getDateLastActivity())) {
                                trelloCard.setBoardId(Optional.ofNullable(card.getIdBoard()).orElse(""));
                                trelloCard.setCardTitle(Optional.ofNullable(card.getName()).orElse(""));
                                trelloCard.setCardDesc(Optional.ofNullable(card.getDesc()).orElse(""));
                                trelloCard.setBoardId(Optional.ofNullable(card.getIdBoard()).orElse(""));
                                trelloCard.setCard(card);
                                trelloCard.setDateLastActivity(card.getDateLastActivity());
                            } else {
                                return;
                            }
                        }
                        UpdateWrapper<TrelloCard> tWrapper = new UpdateWrapper<>();
                        tWrapper.eq("card_id", card.getId());
                        saveOrUpdate(trelloCard, tWrapper);
                    }
            );

        });

    }
}
