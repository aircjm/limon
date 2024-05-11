package com.aircjm.limon.project.card.vo.request;

import cn.hutool.core.util.StrUtil;
import com.aircjm.limon.project.tag.domain.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 请求保存card数据vo
 *
 * @author aircjm
 */
@Data
public class SaveCardRequest {

    @NotEmpty(message = "title(标题) 不能为空")
    @Schema(name = "标题")
    private String title;

    @Schema(name = "内容MD")
    private String contentMd = StrUtil.EMPTY;
    @Schema(name = "内容HTML")
    private String contentHtml = StrUtil.EMPTY;
    @Schema(name = "内容Json")
    private String contentJson = StrUtil.EMPTY;

    @Schema(name = "清单id")
    private Long listId;

    @Schema(name = "项目id")
    private Long projectId;

    @Schema(name = "类型")
    private Integer type;

    @Schema(name = "状态")
    private Integer status;

    @Schema(name = "标签集合")
    private List<Tag> tagList;
}
