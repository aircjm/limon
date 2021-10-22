package com.aircjm.limon.business.card.service;

import com.aircjm.limon.business.anki.ankiconnect.AnkiVo;
import com.aircjm.limon.business.anki.response.AnkiRespVo;
import com.aircjm.limon.business.card.vo.anki.vo.Note;

/**
 * 卡片service接口
 *
 * @author aircjm
 */
public interface AnkiService {

    AnkiRespVo postAnki(AnkiVo ankiVo);


    /**
     * 新增卡片
     *
     * @param note 具体卡片
     * @return 请求返回结果
     */
    AnkiRespVo addNote(Note note);
}
