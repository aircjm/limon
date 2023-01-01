package com.aircjm.limon.project.card.service.impl;

import com.aircjm.limon.project.anki.ankiconnect.AnkiVo;
import com.aircjm.limon.project.anki.response.AnkiRespVo;
import com.aircjm.limon.project.card.service.AnkiService;
import com.aircjm.limon.project.card.vo.anki.vo.Note;
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


//    @Resource
//    private RestTemplate restTemplate;

//    @Resource
//    private ISysConfigService configService;

    @Override
    public AnkiRespVo postAnki(AnkiVo ankiVo) {
        HttpHeaders headers = new HttpHeaders();
//        String ankiUrl = configService.selectConfigByKey("anki.connect.url");
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
        // todo
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        log.info("调用anki接口入参为:{}", JSON.toJSONString(ankiVo));
//        JSONObject jsonObj = JSONObject.parseObject(JSONObject.toJSONString(ankiVo));
//        HttpEntity<String> formEntity = new HttpEntity<>(jsonObj.toString(), headers);
//        String result = restTemplate.postForObject(ankiUrl, formEntity, String.class);
//        log.info("调用anki接口回参为:{}", JSON.toJSONString(result));
//        return JSON.parseObject(result, AnkiRespVo.class);
        return null;
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
