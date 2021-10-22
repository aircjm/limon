package com.aircjm.limon.business.card.vo.request;

import cn.hutool.core.util.StrUtil;
import com.aircjm.limon.business.tag.domain.Tag;
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
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容MD")
    private String contentMd = "";
    @ApiModelProperty(value = "内容HTML")
    private String contentHtml = "";
    @ApiModelProperty(value = "内容Json")
    private String contentJson = "";

    @ApiModelProperty(value = "清单id")
    private Long listId;

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "标签集合")
    private List<Tag> tagList;
}
