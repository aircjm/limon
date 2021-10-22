package com.aircjm.limon.business.card.vo.response;


import com.aircjm.limon.business.tag.domain.Tag;
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
     * 清单集合id
     */
    @ApiModelProperty(value = "清单集合id")
    private Long listId;

    private List<Tag> tagList;

}
