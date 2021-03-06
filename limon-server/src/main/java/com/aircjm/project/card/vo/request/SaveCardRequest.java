package com.aircjm.project.card.vo.request;

import com.aircjm.common.utils.StringUtils;
import com.aircjm.project.tag.domain.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 请求保存card数据vo
 *
 * @author aircjm
 */
@Data
public class SaveCardRequest {

    @NotEmpty(message = "title(标题) 不能为空")
    private String title;


    @ApiModelProperty(value = "内容")
    private String contentMd = StringUtils.EMPTY;

    private Integer status;

    private List<Tag> tagList;

    private Integer type;

}
