package com.aircjm.limon.project.tag.domain;

import com.aircjm.limon.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "标签")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_tag")
public class Tag extends DbBaseEntity<Tag> {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "标签颜色")
    private String color;
}
