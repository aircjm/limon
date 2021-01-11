package com.aircjm.project.card.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aircjm.common.utils.DateUtils;
import com.aircjm.common.utils.StringUtils;
import com.aircjm.framework.message.MessageService;
import com.aircjm.project.card.domain.Task;
import com.aircjm.project.card.domain.TaskAttachment;
import com.aircjm.project.card.mapper.RecordMapper;
import com.aircjm.project.card.service.TaskService;
import com.aircjm.project.card.vo.request.QueryTaskRequest;
import com.aircjm.project.card.vo.request.SaveTaskRequest;
import com.aircjm.project.card.vo.response.TaskDetailResponse;
import com.aircjm.project.system.service.AliyunOssService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author aircjm
 */
@Slf4j
@Service
public class TaskServiceImpl extends ServiceImpl<RecordMapper, Task> implements TaskService {

    @Resource
    private MessageService emailService;

    @Resource
    private AliyunOssService aliyunOssService;



    @Override
    public void save(SaveTaskRequest request) {

        if (Objects.isNull(request.getId())) {
            Task task = Task.builder()
                    .title(request.getTitle())
                    .taskDesc(request.getTaskDesc())
                    .taskHtml("")
                    .type(request.getType())
                    .status(request.getStatus())
                    .startTime(request.getStartTime())
                    .endTime(request.getEndTime())
                    .dueTime(request.getDueTime())
                    .build();
            task.setCreateTime(LocalDateTime.now());
            saveOrUpdate(task);
        } else {
            Task task = BeanUtil.copyProperties(request, Task.class);
            saveOrUpdate(task);
        }

    }

    @Override
    public void noticeAdd() {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        LocalDateTime now = LocalDateTime.now();
        queryWrapper.ge("update_time", now.plusMinutes(-30L));
        Task one = getOne(queryWrapper);
        if (Objects.isNull(one) && DateUtils.isWorkTime(now)) {
            // 消息通知
            emailService.sendNoticeMessage("hello", "world", 1L);
        }
    }

    @Override
    public Page<TaskDetailResponse> list(QueryTaskRequest request) {
        LambdaQueryWrapper<Task> wrapper = new QueryWrapper<Task>().lambda()
                .orderByDesc(Task::getStatus)
                .orderByDesc(Task::getId)
                .like(StringUtils.isNotEmpty(request.getTitle()), Task::getTitle, request.getTitle())
                .eq(Objects.nonNull(request.getStatus()), Task::getStatus, request.getStatus());
        Page<Task> taskPage = page(request, wrapper);
        List<TaskDetailResponse> taskDetailResponseList = taskPage.getRecords().stream().map(this::getTaskDetailResponse).collect(Collectors.toList());
        Page<TaskDetailResponse> responsePage = new Page<>();
        responsePage.setTotal(taskPage.getTotal());
        responsePage.setSize(taskPage.getSize());
        responsePage.setRecords(taskDetailResponseList);
        return responsePage;
    }

    private TaskDetailResponse getTaskDetailResponse(Task task) {
        TaskDetailResponse response = new TaskDetailResponse();
        BeanUtils.copyProperties(task, response);
        return response;
    }

    @Override
    public TaskDetailResponse detail(Long id) {
        Task task = getById(id);
        return getTaskDetailResponse(task);
    }

    @Override
    public List<TaskAttachment> uploadFileList(List<MultipartFile> files, Long taskId, String username) {
        List<TaskAttachment> attachmentList = files.stream().map(file -> {
            String url = aliyunOssService.putObjectGetUrl(file, file.getOriginalFilename());
            TaskAttachment taskAttachment = new TaskAttachment();
            taskAttachment.setUrl(url);
            taskAttachment.setFileName(file.getOriginalFilename());
            taskAttachment.setTaskId(taskId);
            return taskAttachment;
        }).collect(Collectors.toList());
        // todo 关联taskId和文件url

        return attachmentList;

    }
}
