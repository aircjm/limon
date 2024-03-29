package com.aircjm.limon.project.card.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.aircjm.limon.project.card.domain.Task;
import com.aircjm.limon.project.card.domain.TaskAttachment;
import com.aircjm.limon.project.card.mapper.TaskMapper;
import com.aircjm.limon.project.card.service.TaskService;
import com.aircjm.limon.project.card.vo.request.QueryTaskRequest;
import com.aircjm.limon.project.card.vo.request.SaveTaskRequest;
import com.aircjm.limon.project.card.vo.response.TaskDetailResponse;
import com.aircjm.limon.project.oss.service.AliyunOssService;
import com.aircjm.limon.system.exception.CustomException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author aircjm
 */
@Slf4j
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

//    @Resource
//    private MessageService emailService;

    @Resource
    private AliyunOssService aliyunOssService;



    @Override
    public void save(SaveTaskRequest request) {
        Task task = BeanUtil.copyProperties(request, Task.class);
        if (Objects.isNull(task.getId())) {
            log.info("开始处理新增");
            if (StrUtil.isEmpty(request.getTitle())) {
                throw new CustomException("title is not empty");
            }
            task.setCreateTime(LocalDateTime.now());
            saveOrUpdate(task);
        } else {
            saveOrUpdate(task);
        }

    }

    @Override
    public void noticeAdd() {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        LocalDateTime now = LocalDateTime.now();
        queryWrapper.ge("update_time", now.plusMinutes(-30L));
        Task one = getOne(queryWrapper);
        if (Objects.isNull(one)) {
            // todo 消息通知
//            emailService.sendNoticeMessage("hello", "world", 1L);
        }
    }

    @Override
    public Page<TaskDetailResponse> list(QueryTaskRequest request) {
        LambdaQueryWrapper<Task> wrapper = new QueryWrapper<Task>().lambda()
                .orderByDesc(Task::getTaskStatus)
                .orderByDesc(Task::getId)
                .eq(Task::getIsDel, 0)
                .like(StrUtil.isNotEmpty(request.getName()), Task::getTitle, request.getName())
                .eq(Objects.nonNull(request.getStatus()), Task::getTaskStatus, request.getStatus());
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

    @Override
    public void del(Long id) {
        removeById(id);
        // todo 添加操作日志
    }
}
