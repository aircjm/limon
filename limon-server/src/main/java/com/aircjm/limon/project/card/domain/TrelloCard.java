package com.aircjm.limon.project.card.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.julienvey.trello.domain.Card;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author aircjm
 */
@Schema(name = "Trello 卡片")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "trello_card", autoResultMap = true)
public class TrelloCard {

    @TableId(type = IdType.INPUT)
    private Long id;

    @Schema(name = "卡片id")
    private String cardId;

    @Schema(name = "看板id")
    private String boardId;

    @Schema(name = "标题")
    private String cardTitle;

    @Schema(name = "内容描述")
    private String cardDesc;

    @Schema(name = "卡片内容")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Card card;

    @Schema(name = "最后更新时间")
    private Date dateLastActivity;


}
