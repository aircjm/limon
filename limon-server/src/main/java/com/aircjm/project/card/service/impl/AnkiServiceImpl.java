package com.aircjm.project.card.service.impl;

import com.aircjm.project.anki.ankiconnect.AnkiVo;
import com.aircjm.project.anki.response.AnkiRespVo;
import com.aircjm.project.card.service.AnkiService;
import com.aircjm.project.card.vo.anki.vo.Note;
import com.aircjm.project.system.service.ISysConfigService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    private ISysConfigService configService;

    @Override
    public AnkiRespVo postAnki(AnkiVo ankiVo) {
        HttpHeaders headers = new HttpHeaders();
        String ankiUrl = configService.selectConfigByKey("anki.connect.url");
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        log.info("调用anki接口入参为:{}", JSON.toJSONString(ankiVo));
        JSONObject jsonObj = JSONObject.parseObject(JSONObject.toJSONString(ankiVo));
        HttpEntity<String> formEntity = new HttpEntity<>(jsonObj.toString(), headers);
        String result = restTemplate.postForObject(ankiUrl, formEntity, String.class);
        log.info("调用anki接口回参为:{}", JSON.toJSONString(result));
        return JSON.parseObject(result, AnkiRespVo.class);
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
