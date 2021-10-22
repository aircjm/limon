package com.aircjm.limon.business.card.vo.anki.vo;

import lombok.Data;

import java.util.List;

@Data
public class Note {
    private String deckName;
    private String modelName;
    private Fields fields;
    private Audio audio;
    private List<String> tags;
}
