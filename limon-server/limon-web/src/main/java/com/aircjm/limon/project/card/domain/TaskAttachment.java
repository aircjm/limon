package com.aircjm.limon.project.card.domain;


import com.aircjm.limon.common.vo.DbBaseEntity;
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

    @ApiModelProperty(value = "附件类型 文件后缀名")
    private String fileType;

    @ApiModelProperty(value = "文件名")
    private  String fileName;

    @ApiModelProperty(value = "附件url")
    private String url;

}
