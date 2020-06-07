package com.aircjm.project.card.service;

import com.aircjm.project.card.domain.CellCard;
import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.julienvey.trello.domain.Card;

/**
 * 卡片service接口
 *
 * @author aircjm
 */
public interface CellCardService extends IService<CellCard> {

    void saveCard(SaveCardRequest request);

    Page<Card> getCardList(GetCardRequest request);

    /**
     * 全量更新卡片
     */
    void updateAllCard();
}
