package com.aircjm.limon.business.card.controller;

import com.aircjm.limon.business.BaseController;
import com.aircjm.limon.business.card.domain.TaskAttachment;
import com.aircjm.limon.business.card.service.TaskService;
import com.aircjm.limon.business.card.vo.request.DelTaskRequest;
import com.aircjm.limon.business.card.vo.request.QueryTaskRequest;
import com.aircjm.limon.business.card.vo.request.SaveTaskRequest;
import com.aircjm.limon.business.card.vo.response.TaskDetailResponse;
import com.aircjm.limon.common.vo.RestResult;
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
 */
@RestController
@RequestMapping(value = "/api/task/")
@Api(value = "记录API")
public class TaskController extends BaseController {


    @Resource
    private TaskService taskService;

    /**
     * 新增或者更新记录
     *
     * @param request request
     * @return 结果
     */
    @PostMapping("/save")
    public RestResult save(@RequestBody @Valid SaveTaskRequest request) {
        taskService.save(request);
        return RestResult.successEmpty();
    }


    /**
     * 新增或者更新记录
     *
     * @param request request
     * @return 结果
     */
    @PostMapping("/del")
    public RestResult del(@RequestBody @Valid DelTaskRequest request) {
        taskService.del(request.getId());
        return RestResult.successEmpty();
    }


    /**
     * 详情
     *
     * @param id request
     * @return 结果
     */
    @GetMapping("/detail")
    public RestResult<TaskDetailResponse> detail(@RequestParam(value = "id") Long id) {
        return RestResult.successData(taskService.detail(id));
    }


    /**
     * 监听实际记录是否超时未新增
     *
     * @return 结果
     */
    @GetMapping("/notice")
    public RestResult noticeAdd() {
        taskService.noticeAdd();
        return RestResult.successEmpty();
    }

    /**
     * 列表搜索
     *
     * @return 结果
     */
    @PostMapping("/list")
    public RestResult<Page<TaskDetailResponse>> list(@RequestBody @Valid QueryTaskRequest request) {
        Page<TaskDetailResponse> responsePage = taskService.list(request);
        return RestResult.successData(responsePage);
    }

    /**
     * 上传文件集合
     *
     * @return 结果
     */
    @PostMapping(value = "/upload")
    public RestResult<List<TaskAttachment>> uploadFileList(@RequestParam(value = "file[]") List<MultipartFile> files, @RequestParam(value = "id") Long id) {
        return RestResult.successData(taskService.uploadFileList(files, id, getUsername()));
    }
}
