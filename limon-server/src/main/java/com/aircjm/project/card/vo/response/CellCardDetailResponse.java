package com.aircjm.project.card.vo.response;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author aircjm
 */
@ApiModel(value = "卡片详情返回")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CellCardDetailResponse {

    private Long id;

    private String cardId;

    private String cardTitle;

    private String cardDesc;

    private String descHtml;

    private Integer status;

    private LocalDateTime trelloUpdateTime;

    private String listId;

    private String boardId;

}
