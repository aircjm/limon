package com.aircjm.project.tag.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 请求保存record数据vo
 *
 * @author aircjm
 */
@Data
public class SaveTagRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @NotEmpty(message = "名称 不能为空")
    private String name;
}
