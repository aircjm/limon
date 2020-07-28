package com.aircjm.project.card.domain;


import com.aircjm.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.julienvey.trello.domain.Card;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Trello 卡片")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "trello_card")
public class TrelloCard extends DbBaseEntity<TrelloCard> {


    @TableId(type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "标题")
    private String cardTitle;

    @ApiModelProperty(value = "内容描述")
    private String cardDesc;

    @ApiModelProperty(value = "卡片内容")
    private Card card;

    @ApiModelProperty(value = "最后更新时间")
    private Date dateLastActivity;


}
