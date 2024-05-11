package com.aircjm.limon.project.anki.ankiconnect;

import lombok.Data;

import java.util.Map;

/**
 * @author aircjm
 */
@Data
public class AnkiVo {

    private String action;

    private Integer version = 6;

    private Map<String, Object> params;
}
