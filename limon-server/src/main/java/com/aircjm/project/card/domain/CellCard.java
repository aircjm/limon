package com.aircjm.project.card.domain;


import com.aircjm.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
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


    private String cardTitle;


    private String cardDesc;


    private String descHtml;

    private Integer status;

    private LocalDateTime trelloUpdateTime;


    private String listId;

    private String boardId;

}
