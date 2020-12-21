package com.aircjm.project.card.vo.response;


import com.aircjm.framework.aspectj.lang.annotation.Excel;
import com.aircjm.project.tag.domain.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author aircjm
 */
@ApiModel(value = "任务详情返回")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDetailResponse {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Excel(name = "Question")
    @ApiModelProperty(value = "标题")
    private String title;


    @ApiModelProperty(value = "卡片内容描述")
    @Excel(name = "Answer")
    private String taskDesc;

    @ApiModelProperty(value = "卡片内容描述HTML")
    private String taskHtml;

    @ApiModelProperty(value = "卡片状态")
    private Integer status;


    private String listId;

    @ApiModelProperty(value = "截止时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueTime;


    @Builder.Default
    @ApiModelProperty(value = "细胞内容类型，支持html1，markdown0")
    private Integer type = 0;


    private List<Tag> tagList;

}
