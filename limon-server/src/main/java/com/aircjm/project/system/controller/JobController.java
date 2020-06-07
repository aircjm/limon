package com.aircjm.project.system.controller;

import com.aircjm.common.vo.RestResponse;
import com.aircjm.project.card.service.CellCardService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/system/job")
@RestController
public class JobController {

    @Resource
    private CellCardService cellCardService;

    @Async
    @PostMapping("/refreshAllCard")
    public RestResponse refreshAllCard() {
        cellCardService.updateAllCard();
        return RestResponse.successEmpty();
    }
}
