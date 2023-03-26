package com.aircjm.limon.project.card.domain;


import com.aircjm.limon.system.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@Schema(name = "任务")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_task_attachment")
public class TaskAttachment extends DbBaseEntity<TaskAttachment> {

    private Long id;

    private Long taskId;

    @Schema(name = "附件类型 文件后缀名")
    private String fileType;

    @Schema(name = "文件名")
    private  String fileName;

    @Schema(name = "附件url")
    private String url;

}
