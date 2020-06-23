package com.aircjm.project.card.vo.anki.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Fields {
    @JSONField(name = "Front")
    private String front;
    @JSONField(name = "Back")
    private String back;
}
