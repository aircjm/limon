package com.aircjm.project.card.vo.response;


import com.aircjm.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author aircjm
 */
@ApiModel(value = "任务详情返回")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDetailResponse {

    private Long id;

    @Excel(name = "Question")
    @ApiModelProperty(value = "标题")
    private String title;

    @Excel(name = "Answer")
    private String cardDesc;

    @ApiModelProperty(value = "卡片状态")
    private Integer status;

    private LocalDateTime trelloUpdateTime;

    private String listId;


    @Builder.Default
    @ApiModelProperty(value = "细胞内容类型，支持html1，markdown0")
    private Integer type = 0;

    @ApiModelProperty(value = "卡片内容描述")
    private String taskDesc;

    @ApiModelProperty(value = "卡片内容描述HTML")
    private String taskHtml;
}
