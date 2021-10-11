package com.aircjm.limon.project.card.vo.anki.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Fields {
    @JSONField(name = "Question")
    private String front;
    @JSONField(name = "Answer")
    private String back;
}
