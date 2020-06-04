package com.aircjm.project.card.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求获取Card数据vo
 *
 * @author aircjm
 */
@Data
public class GetCardRequest {

    @ApiModelProperty(value = "卡片状态")
    private Integer status;
}
