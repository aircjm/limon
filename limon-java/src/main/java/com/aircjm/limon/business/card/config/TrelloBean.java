package com.aircjm.limon.business.card.config;

import org.springframework.context.annotation.Configuration;

/**
 * Trello配置
 *
 * @author aircjm
 */
@Configuration
public class TrelloBean {
//
//    @Resource
//    private ISysConfigService sysConfigService;
//
//    @Bean
//    public Trello getTrelloApi() {
//        String trelloConfig = sysConfigService.selectConfigByKey("trello.config");
//        if (StrUtil.isNotBlank(trelloConfig)) {
//            Map<String, String> trelloMap = JSONUtil.toBean(trelloConfig, new TypeReference<Map<String, String>>() {
//            }, true);
//            return new TrelloImpl(trelloMap.get("trello.api.key"), trelloMap.get("trello.token"), new ApacheHttpClient());
//        } else {
//            return new TrelloImpl(null, null, new ApacheHttpClient());
//        }
//
//    }
}
