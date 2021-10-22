package com.aircjm.limon.business.anki.ankiconnect;

import lombok.Data;

import java.util.Map;

@Data
public class AnkiVo {

    private String action;

    private Integer version = 6;

    private Map<String, Object> params;
}
