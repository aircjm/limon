package com.aircjm.limon.project.card.domain;


import com.aircjm.limon.system.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "卡片")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "anki_card")
public class AnkiCard extends DbBaseEntity<AnkiCard> {

    @TableId(type = IdType.INPUT)
    private String id;

    private String cardId;


    @ApiModelProperty(value = "卡片标题")
    private String cardTitle;


    private String listId;

    private String boardId;

//
//    @ApiModelProperty(value = "细胞内容类型，支持html，markdown ")
//    private String descType;

    @ApiModelProperty(value = "卡片内容描述")
    private String cardDesc;

    @ApiModelProperty(value = "anki 笔记id")
    private String ankiNoteId;

    @ApiModelProperty(value = "anki note 更新时间")
    private LocalDateTime ankiNoteUpdateTime;


    @ApiModelProperty(value = "卡片内容描述HTML")
    private String descHtml;

    @ApiModelProperty(value = "卡片状态")
    private Integer status;

    private Date trelloUpdateTime;


}
