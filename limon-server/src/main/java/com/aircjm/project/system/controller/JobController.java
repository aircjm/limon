package com.aircjm.project.system.controller;

import com.aircjm.common.vo.RestResponse;
import com.aircjm.framework.aspectj.lang.annotation.Log;
import com.aircjm.framework.aspectj.lang.enums.BusinessType;
import com.aircjm.project.card.service.AnkiCardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/system/job")
@RestController
public class JobController {

    @Resource
    private AnkiCardService ankiCardService;

    @Log(title = "刷新卡片数据", businessType = BusinessType.IMPORT)
    @GetMapping("/refreshAllCard")
    public RestResponse refreshAllCard() {
        ankiCardService.asyncUpdateAllCard();
        return RestResponse.successEmpty();
    }
}
