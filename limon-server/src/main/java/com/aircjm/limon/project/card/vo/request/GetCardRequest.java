package com.aircjm.limon.project.card.vo.request;

import com.aircjm.limon.project.card.domain.AnkiCard;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(name = "卡片状态")
    private Integer ankiStatus;

    @Schema(name = "状态")
    private Integer status;
}
