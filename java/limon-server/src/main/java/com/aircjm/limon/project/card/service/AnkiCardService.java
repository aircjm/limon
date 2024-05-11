package com.aircjm.limon.project.card.service;


import com.aircjm.limon.project.card.vo.request.GetCardRequest;
import com.aircjm.limon.project.card.vo.request.SaveCardRequest;
import com.aircjm.limon.project.card.vo.request.SetAnkiRequest;
import com.aircjm.limon.project.card.vo.response.TaskDetailResponse;
import com.aircjm.limon.system.vo.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 卡片service接口
 *
 * @author aircjm
 */
public interface AnkiCardService {

    void saveCard(SaveCardRequest request);

    Page<TaskDetailResponse> getCardList(GetCardRequest request);

    Result exportCard(GetCardRequest request);

    void asyncUpdateAllCard();

    void setAnki(SetAnkiRequest request);
}
