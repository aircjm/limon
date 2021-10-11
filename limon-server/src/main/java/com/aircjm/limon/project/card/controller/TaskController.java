package com.aircjm.limon.project.card.controller;

import com.aircjm.limon.utils.SecurityUtils;
import com.aircjm.limon.vo.RestReult;
import com.aircjm.limon.project.card.domain.TaskAttachment;
import com.aircjm.limon.project.card.service.TaskService;
import com.aircjm.limon.project.card.vo.request.DelTaskRequest;
import com.aircjm.limon.project.card.vo.request.QueryTaskRequest;
import com.aircjm.limon.project.card.vo.request.SaveTaskRequest;
import com.aircjm.limon.project.card.vo.response.TaskDetailResponse;
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
    public RestReult save(@RequestBody @Valid SaveTaskRequest request) {
        taskService.save(request);
        return RestReult.successEmpty();
    }


    /**
     * 新增或者更新记录
     *
     * @param request request
     * @return 结果
     */
    @PostMapping("/del")
    public RestReult del(@RequestBody @Valid DelTaskRequest request) {
        taskService.del(request.getId());
        return RestReult.successEmpty();
    }


    /**
     * 详情
     *
     * @param id request
     * @return 结果
     */
    @GetMapping("/detail")
    public RestReult<TaskDetailResponse> detail(@RequestParam(value = "id") Long id) {
        return RestReult.successData(taskService.detail(id));
    }


    /**
     * 监听实际记录是否超时未新增
     *
     * @return 结果
     */
    @GetMapping("/notice")
    public RestReult noticeAdd() {
        taskService.noticeAdd();
        return RestReult.successEmpty();
    }

    /**
     * 列表搜索
     *
     * @return 结果
     */
    @PostMapping("/list")
    public RestReult<Page<TaskDetailResponse>> list(@RequestBody @Valid QueryTaskRequest request) {
        Page<TaskDetailResponse> responsePage = taskService.list(request);
        return RestReult.successData(responsePage);
    }

    /**
     * 上传文件集合
     *
     * @return 结果
     */
    @PostMapping(value = "/upload")
    public RestReult<List<TaskAttachment>> uploadFileList(@RequestParam(value = "file[]") List<MultipartFile> files, @RequestParam(value = "id") Long id) {
        return RestReult.successData(taskService.uploadFileList(files, id, SecurityUtils.getUsername()));
    }
}
