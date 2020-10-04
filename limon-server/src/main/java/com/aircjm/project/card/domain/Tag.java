package com.aircjm.project.card.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aircjm
 */
@ApiModel(value = "tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "tag", autoResultMap = true)
public class Tag {

    private Long id;

    @ApiModelProperty(value = "父节点id")
    private Long parentId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "颜色")
    private String color;
}
