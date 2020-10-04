package com.aircjm.project.card.service;

import com.aircjm.project.card.vo.request.SaveRecordRequest;

public interface RecordService {

    /**
     * 保存记录
     *
     * @param request request
     */
    void save(SaveRecordRequest request);
}
