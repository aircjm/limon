package com.aircjm.project.card.service;

import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.julienvey.trello.domain.Card;

/**
 * 卡片service接口
 *
 * @author aircjm
 */
public interface CardService {

    void saveCard(SaveCardRequest request);

    Page<Card> getCardList(GetCardRequest request);
}
