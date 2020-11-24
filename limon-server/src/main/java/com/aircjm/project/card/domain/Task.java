package com.aircjm.project.card.domain;


import com.aircjm.common.vo.DbBaseEntity;
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

    @ApiModelProperty(value = "标题")
    private String title;

    @Builder.Default
    @ApiModelProperty(value = "细胞内容类型，支持html1，markdown0")
    private Integer type = 0;

    @ApiModelProperty(value = "卡片内容描述")
    private String taskDesc;

    @ApiModelProperty(value = "卡片内容描述HTML")
    private String taskHtml;

    @ApiModelProperty(value = "卡片状态")
    private Integer status;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime dueTime;
    private LocalDateTime doneTime;

}
