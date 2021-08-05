package com.aircjm.limon.project.card.domain;


import com.aircjm.limon.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "任务")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_task")
public class Task extends DbBaseEntity<Task> {

    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @Builder.Default
    @ApiModelProperty(value = "内容类型，支持html1，markdown0")
    private Integer descType = 0;

    @ApiModelProperty(value = "内容描述")
    private String taskDesc;

    @ApiModelProperty(value = "内容描述HTML")
    private String taskHtml;

    @ApiModelProperty(value = "任务状态")
    private Integer status;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime dueTime;
    private LocalDateTime doneTime;
}
