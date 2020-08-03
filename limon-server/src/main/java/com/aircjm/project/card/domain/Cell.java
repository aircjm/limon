package com.aircjm.project.card.domain;


import com.aircjm.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "卡片")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "cell_card")
public class Cell extends DbBaseEntity<Cell> {

    @TableId(type = IdType.INPUT)
    private String id;

    private String cardId;


    @ApiModelProperty(value = "卡片标题")
    private String cardTitle;


    private String listId;

    private String boardId;


    @ApiModelProperty(value = "细胞内容类型，支持html，markdown ")
    private String descType;

    @ApiModelProperty(value = "卡片内容描述")
    private String cardDesc;


    @ApiModelProperty(value = "卡片内容描述HTML")
    private String descHtml;

    @ApiModelProperty(value = "卡片状态")
    private Integer status;

    private LocalDateTime trelloUpdateTime;

}
