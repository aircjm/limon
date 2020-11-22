package com.aircjm.project.card.service.impl;

import com.aircjm.common.utils.DateUtils;
import com.aircjm.common.utils.IdUtils;
import com.aircjm.framework.message.MessageService;
import com.aircjm.project.card.domain.Task;
import com.aircjm.project.card.mapper.RecordMapper;
import com.aircjm.project.card.service.TaskService;
import com.aircjm.project.card.vo.request.QueryTaskRequest;
import com.aircjm.project.card.vo.request.SaveTaskRequest;
import com.aircjm.project.card.vo.response.TaskDetailResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

    @Override
    public void save(SaveTaskRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .taskDesc(request.getContent())
                .taskHtml(request.getContent())
                .type(request.getType())
                .status(request.getStatus())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .dueTime(request.getDueTime())
                .build();
        if (Objects.isNull(request.getId())) {
            task.setId(IdUtils.getId());
            task.setCreateTime(LocalDateTime.now());
        }

        saveOrUpdate(task);
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
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        Page<Task> taskPage = page(request, queryWrapper);
        List<TaskDetailResponse> taskDetailResponseList = taskPage.getRecords().stream().map(task -> {
            TaskDetailResponse response = new TaskDetailResponse();
            BeanUtils.copyProperties(task, response);
            return response;
        }).collect(Collectors.toList());
        Page<TaskDetailResponse> responsePage = new Page<>();
        responsePage.setTotal(taskPage.getTotal());
        responsePage.setSize(taskPage.getSize());
        responsePage.setRecords(taskDetailResponseList);
        return responsePage;
    }
}
