package com.aircjm.limon.business.card.service;

import com.aircjm.limon.business.card.domain.AnkiCard;
import com.aircjm.limon.business.card.vo.request.GetCardRequest;
import com.aircjm.limon.business.card.vo.request.SaveCardRequest;
import com.aircjm.limon.business.card.vo.request.SetAnkiRequest;
import com.aircjm.limon.business.card.vo.response.TaskDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 卡片service接口
 *
 * @author aircjm
 */
public interface AnkiCardService extends IService<AnkiCard> {

    void saveCard(SaveCardRequest request);

    Page<TaskDetailResponse> getCardList(GetCardRequest request);

    String exportCard(GetCardRequest request);

    void asyncUpdateAllCard();

    void setAnki(SetAnkiRequest request);
}
