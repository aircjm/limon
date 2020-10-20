package com.aircjm.framework.config;

import com.aircjm.project.system.domain.SysConfig;
import com.aircjm.project.system.mapper.SysConfigMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 邮箱配置
 *
 * @author aircjm
 */
@Configuration
public class MailConfig {


    @Resource
    private SysConfigMapper sysConfigMapper;

    @Bean()
    public JavaMailSender customMailSender() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey("mailConfig");
        List<SysConfig> configList = sysConfigMapper.selectConfigList(sysConfig);
        Map<String, String> emailConfigMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(configList)) {
            emailConfigMap = JSON.parseObject(configList.get(0).getConfigValue(), new TypeReference<Map<String, String>>() {
            });
        }
        javaMailSenderImpl.setHost(emailConfigMap.get("host"));
        javaMailSenderImpl.setProtocol(emailConfigMap.get("protocol"));
        javaMailSenderImpl.setUsername(emailConfigMap.get("username"));
        javaMailSenderImpl.setPassword(emailConfigMap.get("password"));
        javaMailSenderImpl.setPort(Integer.parseInt(emailConfigMap.get("port")));

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.starttls.required", "true");
        javaMailSenderImpl.setJavaMailProperties(javaMailProperties);
        return javaMailSenderImpl;
    }
}
