package com.aircjm.limon.project.card.vo.anki.vo;

import lombok.Data;

import java.util.List;
/**
 * @author aircjm
 */
@Data
public class Note {
    private String deckName;
    private String modelName;
    private Fields fields;
    private Audio audio;
    private List<String> tags;
}
