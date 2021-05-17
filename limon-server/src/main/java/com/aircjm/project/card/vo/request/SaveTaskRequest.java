package com.aircjm.project.card.vo.request;

import com.aircjm.common.utils.StringUtils;
import com.aircjm.project.tag.domain.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 请求保存record数据vo
 *
 * @author aircjm
 */
@Data
public class SaveTaskRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    private String title;

    @ApiModelProperty(value = "内容")
    private String taskDesc = StringUtils.EMPTY;

    private Integer status;

    private List<Tag> tagList;

    private Integer type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime doneTime;


}
