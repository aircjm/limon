package com.aircjm.limon.project.card.domain;


import com.aircjm.limon.system.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@Schema(name = "卡片")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "anki_card")
public class AnkiCard extends DbBaseEntity<AnkiCard> {

    @TableId(type = IdType.INPUT)
    private String id;

    private String cardId;


    @Schema(name = "卡片标题")
    private String cardTitle;


    private String listId;

    private String boardId;

//
//    @Schema(name = "细胞内容类型，支持html，markdown ")
//    private String descType;

    @Schema(name = "卡片内容描述")
    private String cardDesc;

    @Schema(name = "anki 笔记id")
    private String ankiNoteId;

    @Schema(name = "anki note 更新时间")
    private LocalDateTime ankiNoteUpdateTime;


    @Schema(name = "卡片内容描述HTML")
    private String descHtml;

    @Schema(name = "卡片状态")
    private Integer status;

    private Date trelloUpdateTime;


}
