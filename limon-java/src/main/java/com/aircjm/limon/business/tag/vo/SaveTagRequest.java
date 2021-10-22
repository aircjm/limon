package com.aircjm.limon.business.tag.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 请求保存标签数据vo
 *
 * @author aircjm
 */
@Data
public class SaveTagRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "标签名称")
    @NotEmpty(message = "名称 不能为空")
    private String name;

    @ApiModelProperty(value = "颜色 默认无色")
    private String color = "#fff";
}
