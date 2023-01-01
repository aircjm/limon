package com.aircjm.limon.project.card.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * del 数据vo
 *
 * @author aircjm
 */
@Data
public class DelTaskRequest {

    @NotNull
    @ApiModelProperty(value = "id")
    private Long id;
}
