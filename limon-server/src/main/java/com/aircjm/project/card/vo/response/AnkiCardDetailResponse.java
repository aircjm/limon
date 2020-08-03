package com.aircjm.project.card.vo.response;


import com.aircjm.framework.aspectj.lang.annotation.Excel;
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
public class AnkiCardDetailResponse {

    private Long id;

    private String cardId;

    @Excel(name = "Question")
    private String cardTitle;

    @Excel(name = "Answer")
    private String cardDesc;

    private Integer status;

    private LocalDateTime trelloUpdateTime;

    private String listId;

    private String boardId;


    private String ankiNoteId;
}
