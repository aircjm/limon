package com.aircjm.project.anki.ankiconnect;


import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class NoteVo {
    private String deckName = "Default";

    private String modelName = "Basic";

    private Map<String, String> fields;

    private List<String> tags;

//
//    public static NoteVo convert(YoudaoVo youdaoVo) {
//        if (youdaoVo == null) {
//            return null;
//        }
//        NoteVo noteVo = new NoteVo();
//        noteVo.setDeckName("Default");
//        noteVo.setModelName("Basic");
//        Map<String, String> fields = new HashMap();
//        fields.put("Front", youdaoVo.getFront());
//        fields.put("Back", youdaoVo.getBack());
//        noteVo.setFields(fields);
//        noteVo.setTags(null);
//        return noteVo;
//    }
}

