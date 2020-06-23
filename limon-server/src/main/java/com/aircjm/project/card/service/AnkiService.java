package com.aircjm.project.card.service;

import com.aircjm.project.anki.ankiconnect.AnkiRespVo;
import com.aircjm.project.anki.ankiconnect.AnkiVo;
import com.aircjm.project.anki.ankiconnect.NoteVo;
import com.aircjm.project.card.vo.anki.vo.Note;
import com.aircjm.project.card.vo.request.GetBoardRequest;
import com.julienvey.trello.domain.Board;

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

    AnkiRespVo addNote(Note note);
}
