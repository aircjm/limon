package com.aircjm.limon.project.monitor.controller;

import com.aircjm.limon.framework.web.controller.BaseController;
import com.aircjm.limon.framework.web.domain.AjaxResult;
import com.aircjm.limon.framework.web.domain.Server;
import com.aircjm.limon.project.monitor.service.RescueTimeService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * 服务器监控
 * 
 * @author aircjm
 */
@RestController
@RequestMapping("/rescuetime")
public class RescueTimeController extends BaseController
{
    @Resource
    private RescueTimeService rescueTimeService;


    @GetMapping("/dailySummary")
    public void dailySummary(@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam LocalDate date) {
        // todo 获取当前用户信息
        rescueTimeService.getDailySummaryData(date);
    }

}
