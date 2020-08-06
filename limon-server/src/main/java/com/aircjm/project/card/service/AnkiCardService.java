package com.aircjm.project.card.service;

import com.aircjm.framework.web.domain.AjaxResult;
import com.aircjm.project.card.domain.AnkiCard;
import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import com.aircjm.project.card.vo.request.SetAnkiRequest;
import com.aircjm.project.card.vo.response.AnkiCardDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 卡片service接口
 *
 * @author aircjm
 */
public interface AnkiCardService extends IService<AnkiCard> {

    void saveCard(SaveCardRequest request);

    Page<AnkiCardDetailResponse> getAnkiCardList(GetCardRequest request);

    AjaxResult exportCard(GetCardRequest request);

    /**
     * 全量更新卡片
     */
    void updateAllCard();

    /**
     * 异步全量更新卡片
     */
    void asyncUpdateAllCard();

    void setAnki(SetAnkiRequest request);
}
