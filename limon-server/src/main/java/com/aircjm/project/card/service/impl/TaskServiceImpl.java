package com.aircjm.project.card.service.impl;

import com.aircjm.common.utils.DateUtils;
import com.aircjm.framework.config.ServerConfig;
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
    private ServerConfig serverConfig;

    @Override
    public void save(SaveTaskRequest request) {
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
        if (Objects.isNull(request.getId())) {
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
    public void uploadFileList(List<MultipartFile> files, Long taskId, String username) {
//        try
//        {
//            String name = StringUtils.EMPTY;
//            // 上传文件路径
//            String filePath = SystemConfig.getUploadPath();
//            for (MultipartFile multipartFile : files) {
//                name = FileUploadUtils.upload(filePath, multipartFile);
//            }
//            // 上传并返回新文件名称
//            String url = serverConfig.getUrl() + name;
//            AjaxResult ajax = AjaxResult.success();
//            ajax.put("fileName", name);
//            ajax.put("url", url);
//            return ajax;
//        }
//        catch (Exception e)
//        {
//            return AjaxResult.error(e.getMessage());
//        }
    }
}
