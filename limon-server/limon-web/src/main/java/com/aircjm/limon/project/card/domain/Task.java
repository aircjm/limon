package com.aircjm.limon.project.card.domain;

import com.aircjm.limon.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "任务")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_task")
public class Task extends DbBaseEntity<Task> {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容的html
     */
    @ApiModelProperty(value = "内容的html")
    private String descHtml;

    /**
     * 内容的md
     */
    @ApiModelProperty(value = "内容的md,")
    private String descMd;

    /**
     * 描述的json
     */
    @ApiModelProperty(value = "描述的json")
    private String descJson;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer taskStatus;

    /**
     * 是否全天 0：不是 1：是
     */
    @ApiModelProperty(value = "是否全天 0：不是 1：是")
    private Integer allDay;

    /**
     * 时区
     */
    @ApiModelProperty(value = "时区")
    private String timeZone;

    /**
     * 截至时间
     */
    @ApiModelProperty(value = "截至时间")
    private LocalDateTime dueTime;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private LocalDateTime doneTime;

    /**
     * 持续时间-开始时间
     */
    @ApiModelProperty(value = "持续时间-开始时间")
    private LocalDateTime startTime;

    /**
     * 持续时间-结束时间
     */
    @ApiModelProperty(value = "持续时间-结束时间")
    private LocalDateTime endTime;

    /**
     * 提醒时间
     */
    @ApiModelProperty(value = "提醒时间")
    private LocalDateTime reminderTime;

    /**
     * 位置
     */
    @ApiModelProperty(value = "位置")
    private Double position;

    /**
     * 对应的url
     */
    @ApiModelProperty(value = "对应的url")
    private String url;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    /**
     * 所属用户id
     */
    @ApiModelProperty(value = "所属用户id")
    private Long userId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    /**
     * 逻辑删除标识(0.未删除,1.已删除)
     */
    @ApiModelProperty(value = "逻辑删除标识(0.未删除,1.已删除)")
    private Integer isDel;

    /**
     * 清单集合id
     */
    @ApiModelProperty(value = "清单集合id")
    private Long listId;
}

