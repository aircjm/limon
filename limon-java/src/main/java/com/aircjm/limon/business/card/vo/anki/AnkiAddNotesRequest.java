package com.aircjm.limon.business.card.vo.anki;

import com.aircjm.limon.business.card.vo.anki.vo.Note;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class AnkiAddNotesRequest {
    List<Note> notes;
}
