package com.aircjm.limon.framework.config;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.json.JSONUtil;
import com.aircjm.limon.project.system.domain.SysConfig;
import com.aircjm.limon.project.system.mapper.SysConfigMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * 邮箱配置
 *
 * @author aircjm
 */
@Configuration
public class MailConfig {


    @Resource
    private SysConfigMapper sysConfigMapper;


    @Bean
    public MailAccount mailAccount() {
        MailAccount mailAccount = new MailAccount();
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey("mailConfig");
        List<SysConfig> configList = sysConfigMapper.selectConfigList(sysConfig);
        if (CollectionUtils.isNotEmpty(configList)) {
            mailAccount = JSONUtil.toBean(configList.get(0).getConfigValue(), MailAccount.class);
        }
        return mailAccount;
    }
}
