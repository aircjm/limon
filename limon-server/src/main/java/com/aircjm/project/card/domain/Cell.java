package com.aircjm.project.card.domain;


import com.aircjm.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "单元")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_cell")
public class Cell extends DbBaseEntity<Cell> {

    @TableId(type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @Builder.Default
    @ApiModelProperty(value = "细胞内容类型，支持html1，markdown0")
    private Integer type = 0;

    @ApiModelProperty(value = "卡片内容描述")
    private String cellDesc;

    @ApiModelProperty(value = "卡片内容描述HTML")
    private String cellHtml;

    @ApiModelProperty(value = "卡片状态")
    private Integer status;
}
