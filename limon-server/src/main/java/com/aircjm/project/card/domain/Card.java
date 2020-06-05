package com.aircjm.project.card.domain;


import com.aircjm.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Card extends DbBaseEntity<Card> {

    @TableId
    private String cardId;

    private String listId;

    private String boardId;

    private String title;

    private String desc;

    private Integer status;

    private String descHtml;

}
