package com.aircjm.limon.project.tag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


/**
 * 请求保存标签数据vo
 *
 * @author aircjm
 */
@Data
public class SaveTagRequest {

    @Schema(name = "id")
    private Long id;

    @Schema(name = "标签名称")
    @NotEmpty(message = "名称 不能为空")
    private String name;

    @Schema(name = "颜色 默认无色")
    private String color = "#fff";
}
