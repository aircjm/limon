package com.aircjm.limon.project.monitor.service.impl;

import com.aircjm.limon.common.exception.CustomException;
import com.aircjm.limon.project.monitor.service.RescueTimeService;
import com.aircjm.limon.project.system.domain.SysUser;
import com.aircjm.limon.project.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@Service
public class RescueTimeServiceImpl implements RescueTimeService {


    public static final String URI_FORMAT = "https://www.rescuetime.com/anapi/daily_summary_feed?key=%s&pv=interval&rs=minute&rb=%2$s&re=%2$s&format=json";


    @Resource
    private RestTemplate restTemplate;


    @Resource
    private ISysUserService sysUserService;

    @Override
    public Object getDailySummaryData(LocalDate date) {
        SysUser sysUser = sysUserService.selectUserById(null);
        if (Objects.nonNull(sysUser) && Objects.nonNull(sysUser.getUserConfig())) {
            String apiKey = sysUser.getUserConfig().getRescuetimeKey();
            String url = String.format(URI_FORMAT, apiKey, date.toString());
            String data = restTemplate.getForObject(url, String.class);
            return data;
        } else {
            throw new CustomException("用户配置为空");

        }
    }
}
