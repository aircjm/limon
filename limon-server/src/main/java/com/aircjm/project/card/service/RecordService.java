package com.aircjm.project.card.service;

import com.aircjm.project.card.vo.request.SaveRecordRequest;

public interface RecordService {

    /**
     * 保存记录
     *
     * @param request request
     */
    void save(SaveRecordRequest request);

    /**
     * 进行新增提醒
     */
    void noticeAdd();
}
