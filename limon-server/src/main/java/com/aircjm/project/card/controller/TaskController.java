package com.aircjm.project.card.controller;

import com.aircjm.common.vo.RestResponse;
import com.aircjm.project.card.service.TaskService;
import com.aircjm.project.card.vo.request.QueryTaskRequest;
import com.aircjm.project.card.vo.request.SaveTaskRequest;
import com.aircjm.project.card.vo.response.TaskDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * record 使用
 *
 * @author aircjm
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
        Page<TaskDetailResponse> responsePage =  taskService.list(request);
        return RestResponse.successData(responsePage);
    }
}
