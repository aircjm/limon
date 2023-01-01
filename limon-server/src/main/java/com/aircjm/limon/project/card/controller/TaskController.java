package com.aircjm.limon.project.card.controller;

import com.aircjm.limon.project.card.domain.TaskAttachment;
import com.aircjm.limon.project.card.service.TaskService;
import com.aircjm.limon.project.card.vo.request.DelTaskRequest;
import com.aircjm.limon.project.card.vo.request.QueryTaskRequest;
import com.aircjm.limon.project.card.vo.request.SaveTaskRequest;
import com.aircjm.limon.project.card.vo.response.TaskDetailResponse;
import com.aircjm.limon.system.vo.Result;
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
 * @see com.aircjm.limon.project.card.domain.Task
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
    public Result save(@RequestBody @Valid SaveTaskRequest request) {
        taskService.save(request);
        return Result.success();
    }


    /**
     * 新增或者更新记录
     *
     * @param request request
     * @return 结果
     */
    @PostMapping("/del")
    public Result del(@RequestBody @Valid DelTaskRequest request) {
        taskService.del(request.getId());
        return Result.success();
    }


    /**
     * 详情
     *
     * @param id request
     * @return 结果
     */
    @GetMapping("/detail")
    public Result<TaskDetailResponse> detail(@RequestParam(value = "id") Long id) {
        return Result.success(taskService.detail(id));
    }


    /**
     * 监听实际记录是否超时未新增
     *
     * @return 结果
     */
    @GetMapping("/notice")
    public Result noticeAdd() {
        taskService.noticeAdd();
        return Result.success();
    }

    /**
     * 列表搜索
     *
     * @return 结果
     */
    @PostMapping("/list")
    public Result<Page<TaskDetailResponse>> list(@RequestBody @Valid QueryTaskRequest request) {
        Page<TaskDetailResponse> responsePage = taskService.list(request);
        return Result.success(responsePage);
    }

    /**
     * 上传文件集合
     *
     * @return 结果
     */
    @PostMapping(value = "/upload")
    public Result<List<TaskAttachment>> uploadFileList(@RequestParam(value = "file[]") List<MultipartFile> files, @RequestParam(value = "id") Long id) {
        // username todo
        return Result.success(taskService.uploadFileList(files, id, "username"));
    }
}
