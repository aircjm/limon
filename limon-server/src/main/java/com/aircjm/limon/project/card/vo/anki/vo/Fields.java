package com.aircjm.limon.project.card.vo.anki.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author aircjm
 */
@Data
public class Fields {
    // todo 确认下序列化段值
    @JsonProperty(value = "Question")
    private String front;
    @JsonProperty(value = "Answer")
    private String back;
}
