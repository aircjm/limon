package com.aircjm.project.card.domain;


import com.aircjm.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "任务")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_task_attachment")
public class TaskAttachment extends DbBaseEntity<TaskAttachment> {

    private Long id;

    private Long taskId;

    @Builder.Default
    @ApiModelProperty(value = "附件类型，支持html1，markdown0")
    private Integer type = 0;

    @ApiModelProperty(value = "附件url")
    private String url;
}
