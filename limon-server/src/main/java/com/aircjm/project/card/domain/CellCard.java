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
public class CellCard extends DbBaseEntity<CellCard> {

    @TableId(type = IdType.INPUT)
    private String id;

    private String cardId;


    @ApiModelProperty(value = "")
    private String cardTitle;


    @ApiModelProperty(value = "细胞内容类型，支持html，markdown ")
    private String descType;

    private String cardDesc;


    private String descHtml;

    private Integer status;

    private LocalDateTime trelloUpdateTime;


    private String listId;

    private String boardId;

}
