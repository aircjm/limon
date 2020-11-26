package com.aircjm.project.card.controller;

import com.aircjm.common.utils.SecurityUtils;
import com.aircjm.common.vo.RestResponse;
import com.aircjm.project.card.service.TaskService;
import com.aircjm.project.card.vo.request.QueryTaskRequest;
import com.aircjm.project.card.vo.request.SaveTaskRequest;
import com.aircjm.project.card.vo.response.TaskDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * task 使用
 *
 * @author aircjm
 * @see com.aircjm.project.card.domain.Task
 */
@RestController
@RequestMapping(value = "/api/task/")
@Api(value = "记录API")
public class TaskController {


    @Resource
    private TaskService taskService;

    /**
     * 新增或者更新记录
     *
     * @param request request
     * @return 结果
     */
    @PostMapping("/save")
    public RestResponse save(@RequestBody @Valid SaveTaskRequest request) {
        taskService.save(request);
        return RestResponse.successEmpty();
    }


    /**
     * 详情
     *
     * @param id request
     * @return 结果
     */
    @GetMapping("/detail")
    public RestResponse<TaskDetailResponse> detail(@RequestParam(value = "id") Long id) {
        return RestResponse.successData(taskService.detail(id));
    }


    /**
     * 监听实际记录是否超时未新增
     *
     * @return 结果
     */
    @GetMapping("/notice")
    public RestResponse noticeAdd() {
        taskService.noticeAdd();
        return RestResponse.successEmpty();
    }

    /**
     * 监听实际记录是否超时未新增
     *
     * @return 结果
     */
    @PostMapping("/list")
    public RestResponse list(@RequestBody @Valid QueryTaskRequest request) {
        Page<TaskDetailResponse> responsePage = taskService.list(request);
        return RestResponse.successData(responsePage);
    }

    /**
     * 上传文件集合
     *
     * @return 结果
     */
    @PostMapping(value = "/upload")
    public RestResponse uploadFileList(@RequestParam(value = "file[]") List<MultipartFile> files, @RequestParam(value = "id") Long id) {
        taskService.uploadFileList(files, id, SecurityUtils.getUsername());
        return RestResponse.successEmpty();
    }
}
