package com.aircjm.project.card.vo.request;

import com.aircjm.project.card.domain.AnkiCard;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求获取Card数据vo
 *
 * @author aircjm
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetCardRequest extends Page<AnkiCard> {

    @ApiModelProperty(value = "卡片状态")
    private Integer status;
}
