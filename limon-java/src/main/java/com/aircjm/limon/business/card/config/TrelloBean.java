package com.aircjm.limon.business.card.config;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.limon.business.system.SystemConfigService;
import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
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
    private SystemConfigService sysConfigService;

    @Bean
    public Trello getTrelloApi() {
        String trelloConfig = sysConfigService.selectConfigByKey("trello.config");
        if (StrUtil.isNotBlank(trelloConfig)) {
            Map<String, String> trelloMap = JSONUtil.toBean(trelloConfig, new TypeReference<Map<String, String>>() {
            }, true);
            return new TrelloImpl(trelloMap.get("trello.api.key"), trelloMap.get("trello.token"), new ApacheHttpClient());
        } else {
            return new TrelloImpl(null, null, new ApacheHttpClient());
        }

    }
}
