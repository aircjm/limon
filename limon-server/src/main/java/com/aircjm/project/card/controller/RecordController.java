package com.aircjm.project.card.controller;

import com.aircjm.common.vo.RestResponse;
import com.aircjm.project.card.service.RecordService;
import com.aircjm.project.card.vo.request.SaveRecordRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * record 使用
 *
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/api/record/")
@Api(value = "卡片博客API")
public class RecordController {


    @Resource
    private RecordService recordService;

    @PostMapping("/save")
    public RestResponse save(@RequestBody @Valid SaveRecordRequest request) {
        recordService.save(request);
        return RestResponse.successEmpty();
    }
}
