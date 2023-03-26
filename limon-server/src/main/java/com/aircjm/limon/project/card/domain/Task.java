package com.aircjm.limon.project.card.domain;

import com.aircjm.limon.system.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Schema(name = "任务")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_task")
public class Task extends DbBaseEntity<Task> {
    /**
     * id
     */
    @Schema(name = "id")
    private Long id;

    /**
     * 标题
     */
    @Schema(name = "标题")
    private String title;

    /**
     * 内容的html
     */
    @Schema(name = "内容的html")
    private String descHtml;

    /**
     * 内容的md
     */
    @Schema(name = "内容的md,")
    private String descMd;

    /**
     * 描述的json
     */
    @Schema(name = "描述的json")
    private String descJson;

    /**
     * 状态
     */
    @Schema(name = "状态")
    private Integer taskStatus;

    /**
     * 是否全天 0：不是 1：是
     */
    @Schema(name = "是否全天 0：不是 1：是")
    private Integer allDay;

    /**
     * 时区
     */
    @Schema(name = "时区")
    private String timeZone;

    /**
     * 截至时间
     */
    @Schema(name = "截至时间")
    private LocalDateTime dueTime;

    /**
     * 完成时间
     */
    @Schema(name = "完成时间")
    private LocalDateTime doneTime;

    /**
     * 持续时间-开始时间
     */
    @Schema(name = "持续时间-开始时间")
    private LocalDateTime startTime;

    /**
     * 持续时间-结束时间
     */
    @Schema(name = "持续时间-结束时间")
    private LocalDateTime endTime;

    /**
     * 提醒时间
     */
    @Schema(name = "提醒时间")
    private LocalDateTime reminderTime;

    /**
     * 位置
     */
    @Schema(name = "位置")
    private Double position;

    /**
     * 对应的url
     */
    @Schema(name = "对应的url")
    private String url;

    /**
     * 项目id
     */
    @Schema(name = "项目id")
    private Long projectId;

    /**
     * 所属用户id
     */
    @Schema(name = "所属用户id")
    private Long userId;

    /**
     * 清单集合id
     */
    @Schema(name = "清单集合id")
    private Long listId;
}

