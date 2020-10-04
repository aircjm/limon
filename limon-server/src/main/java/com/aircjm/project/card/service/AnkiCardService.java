package com.aircjm.project.card.service;

import com.aircjm.framework.web.domain.AjaxResult;
import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import com.aircjm.project.card.vo.request.SetAnkiRequest;
import com.aircjm.project.card.vo.response.CellCardDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 卡片service接口
 *
 * @author aircjm
 */
public interface AnkiCardService {

    void saveCard(SaveCardRequest request);

    Page<CellCardDetailResponse> getCardList(GetCardRequest request);

    AjaxResult exportCard(GetCardRequest request);

    void asyncUpdateAllCard();

    void setAnki(SetAnkiRequest request);
}
