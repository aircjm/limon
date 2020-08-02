package com.aircjm.project.card.domain;


import com.aircjm.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.julienvey.trello.domain.Card;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author aircjm
 */
@ApiModel(value = "Trello 卡片")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "trello_card", autoResultMap = true)
public class TrelloCard {


    @TableId(type = IdType.INPUT)
    private Long id;


    @ApiModelProperty(value = "卡片id")
    private String cardId;

    @ApiModelProperty(value = "看板id")
    private String boardId;

    @ApiModelProperty(value = "标题")
    private String cardTitle;

    @ApiModelProperty(value = "内容描述")
    private String cardDesc;

    @ApiModelProperty(value = "卡片内容")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Card card;

    @ApiModelProperty(value = "最后更新时间")
    private Date dateLastActivity;


}
