package com.aircjm.project.card.service;

import com.aircjm.project.anki.response.AnkiRespVo;
import com.aircjm.project.anki.ankiconnect.AnkiVo;
import com.aircjm.project.anki.ankiconnect.NoteVo;
import com.aircjm.project.card.vo.anki.vo.Note;

import java.util.List;

/**
 * 卡片service接口
 *
 * @author aircjm
 */
public interface AnkiService {

    AnkiRespVo post(AnkiVo ankiVo);

    /**
     * 批量添加卡片
     *
     * @param noteVos 卡片集合
     * @return 成功集合
     */
    AnkiRespVo addNotes(List<NoteVo> noteVos);

    /**
     * 新增卡片
     *
     * @param note 具体卡片
     * @return 请求返回结果
     */
    AnkiRespVo addNote(Note note);
}
