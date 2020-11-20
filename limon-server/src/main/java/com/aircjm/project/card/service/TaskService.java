package com.aircjm.project.card.service;

import com.aircjm.project.card.domain.Task;
import com.aircjm.project.card.vo.request.QueryTaskRequest;
import com.aircjm.project.card.vo.request.SaveTaskRequest;
import com.aircjm.project.card.vo.response.TaskDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 记录服务
 *
 * @author aircjm
 */
public interface TaskService extends IService<Task> {

    /**
     * 保存记录
     *
     * @param request request
     */
    void save(SaveTaskRequest request);

    /**
     * 进行新增提醒
     */
    void noticeAdd();

    /**
     * 分页查询记录
     *
     * @param request request
     * @return 分页结果
     */
    Page<TaskDetailResponse> list(QueryTaskRequest request);
}
