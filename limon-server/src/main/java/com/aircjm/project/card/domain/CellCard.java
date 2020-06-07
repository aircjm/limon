package com.aircjm.project.card.domain;


import com.aircjm.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

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

    private String listId;

    private String boardId;

    private String title;

    private String desc;

    private Integer status;

    private String descHtml;

}
