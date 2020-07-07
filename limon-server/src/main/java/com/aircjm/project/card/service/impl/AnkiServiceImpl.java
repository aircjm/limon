package com.aircjm.project.card.service.impl;

import com.aircjm.project.anki.response.AnkiRespVo;
import com.aircjm.project.anki.ankiconnect.AnkiVo;
import com.aircjm.project.anki.ankiconnect.NoteVo;
import com.aircjm.project.card.service.AnkiService;
import com.aircjm.project.card.vo.anki.vo.Note;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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

    private static final String ANKI_URL = "http://127.0.0.1:8765";

    @Override
    public AnkiRespVo post(AnkiVo ankiVo) {
        log.info("调用anki接口入参为:{}", JSON.toJSONString(ankiVo));
        ResponseEntity<String> resp = restTemplate.postForEntity(ANKI_URL, ankiVo, String.class);
        System.out.println(JSON.toJSONString(resp));
        return JSON.parseObject(resp.getBody(), AnkiRespVo.class);
    }


    @Override
    public AnkiRespVo addNotes(List<NoteVo> noteVos) {
        AnkiVo ankiVo = new AnkiVo();
        ankiVo.setAction("addNotes");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("notes", noteVos);
        ankiVo.setParams(params);
        return post(ankiVo);
    }

    @Override
    public AnkiRespVo addNote(Note note) {
        AnkiVo ankiVo = new AnkiVo();
        ankiVo.setAction("addNote");
        Map<String, Object> params = Maps.newHashMap();
        params.put("note", note);
        ankiVo.setParams(params);
        return post(ankiVo);
    }

}
