package com.aircjm.project.card.config;

import com.aircjm.project.system.domain.SysConfig;
import com.aircjm.project.system.mapper.SysConfigMapper;
import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Trello配置
 *
 * @author aircjm
 */
@Configuration
public class TrelloBean {

    @Resource
    private SysConfigMapper sysConfigMapper;


    @Bean
    public Trello getTrelloApi() {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey("trello");
        List<SysConfig> trelloConfig = sysConfigMapper.selectConfigList(sysConfig);
        if (CollectionUtils.isNotEmpty(trelloConfig)) {
            Map<String, String> map = trelloConfig.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));
            return new TrelloImpl(map.get("trello.api.key"), map.get("trello.token"), new ApacheHttpClient());
        } else {
            return new TrelloImpl(null, null, new ApacheHttpClient());
        }

    }


}
