package com.aircjm.project.card.service;

import com.aircjm.project.card.domain.Task;
import com.aircjm.project.card.domain.TaskAttachment;
import com.aircjm.project.card.vo.request.QueryTaskRequest;
import com.aircjm.project.card.vo.request.SaveTaskRequest;
import com.aircjm.project.card.vo.response.TaskDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 单个详情查询
     *
     * @param id id
     * @return task
     */
    TaskDetailResponse detail(Long id);

    /**
     * 添加附件
     *
     * @param files    文件
     * @param taskId   taskId
     * @param username 操作用户
     */
    List<TaskAttachment> uploadFileList(List<MultipartFile> files, Long taskId, String username);

    /**
     * 删除任务
     *
     * @param id id
     */
    void del(Long id);
}
