package com.aircjm.limon.project.card.vo.anki.vo;

import lombok.Data;

import java.util.List;

@Data
public class Audio {

    private String url;
    private String filename;
    private String skipHash;
    private List<String> fields;
}
