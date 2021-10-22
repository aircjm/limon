package com.aircjm.limon.business.card.service.impl;

import cn.hutool.json.JSONUtil;
import com.aircjm.limon.business.anki.ankiconnect.AnkiVo;
import com.aircjm.limon.business.anki.response.AnkiRespVo;
import com.aircjm.limon.business.card.service.AnkiService;
import com.aircjm.limon.business.card.vo.anki.vo.Note;
import com.aircjm.limon.business.system.SystemConfigService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author aircjm
 * https://foosoft.net/projects/anki-connect/
 */
@Service
@Slf4j
public class AnkiServiceImpl implements AnkiService {


    @Resource
    private RestTemplate restTemplate;

    @Resource
    private SystemConfigService systemConfigService;

    @Override
    public AnkiRespVo postAnki(AnkiVo ankiVo) {
        HttpHeaders headers = new HttpHeaders();
        String ankiUrl = systemConfigService.selectConfigByKey("anki.connect.url");
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        log.info("调用anki接口入参为:{}", JSONUtil.toJsonStr(ankiVo));
        HttpEntity<String> formEntity = new HttpEntity<>(JSONUtil.toJsonStr(ankiVo), headers);
        String result = restTemplate.postForObject(ankiUrl, formEntity, String.class);
        log.info("调用anki接口回参为:{}", JSONUtil.toJsonStr(result));
        return JSONUtil.toBean(result, AnkiRespVo.class);
    }

    @Override
    public AnkiRespVo addNote(Note note) {
        AnkiVo ankiVo = new AnkiVo();
        ankiVo.setAction("addNote");
        Map<String, Object> params = Maps.newHashMap();
        params.put("note", note);
        ankiVo.setParams(params);
        return postAnki(ankiVo);
    }

}
