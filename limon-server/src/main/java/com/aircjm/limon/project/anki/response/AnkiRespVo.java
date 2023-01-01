package com.aircjm.limon.project.anki.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AnkiRespVo {

    @ApiModelProperty(value = "anki返回结果")
    private String result;

    @ApiModelProperty(value = "错误信息")
    private String error;

}
