package com.aircjm.limon.business.card.vo.anki.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Fields {
    @JsonProperty(value = "Question")
    private String front;
    @JsonProperty(value = "Answer")
    private String back;
}
