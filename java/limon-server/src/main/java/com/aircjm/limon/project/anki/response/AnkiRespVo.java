package com.aircjm.limon.project.anki.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author aircjm
 */
@Data
public class AnkiRespVo {

    @Schema(name = "anki返回结果")
    private String result;

    @Schema(name = "错误信息")
    private String error;

}
