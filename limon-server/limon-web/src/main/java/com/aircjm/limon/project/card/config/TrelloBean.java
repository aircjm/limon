package com.aircjm.limon.project.card.config;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.aircjm.limon.project.system.service.ISysConfigService;
import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Trello配置
 *
 * @author aircjm
 */
@Configuration
public class TrelloBean {

    @Resource
    private ISysConfigService sysConfigService;

    @Bean
    public Trello getTrelloApi() {
        String trelloConfig = sysConfigService.selectConfigByKey("trello.config");
        if (StringUtils.isNotBlank(trelloConfig)) {
            Map<String, String> trelloMap = JSONUtil.toBean(trelloConfig, new TypeReference<Map<String, String>>() {
            }, true);
            return new TrelloImpl(trelloMap.get("trello.api.key"), trelloMap.get("trello.token"), new ApacheHttpClient());
        } else {
            return new TrelloImpl(null, null, new ApacheHttpClient());
        }

    }
}
