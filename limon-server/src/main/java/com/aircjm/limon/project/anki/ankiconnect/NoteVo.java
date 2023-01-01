package com.aircjm.limon.project.anki.ankiconnect;


import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * @author aircjm
 */
@Data
public class NoteVo {
    private String deckName = "Default";

    private String modelName = "Basic";

    private Map<String, String> fields;

    private List<String> tags;
}

