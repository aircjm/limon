package com.aircjm.project.card.vo.request;

import com.aircjm.project.card.domain.CellCard;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求获取Card数据vo
 *
 * @author aircjm
 */
@Data
public class GetCardRequest extends Page<CellCard> {

    @ApiModelProperty(value = "卡片状态")
    private Integer status;
}
