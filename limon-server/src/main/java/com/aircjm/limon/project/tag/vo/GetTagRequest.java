package com.aircjm.limon.project.tag.vo;

import com.aircjm.limon.project.tag.domain.Tag;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * @author aircjm
 */
@Schema(description = "查询标签列表数据请求参数")
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class GetTagRequest extends Page<Tag> {

    @Schema(name = "标签名称 支持模糊搜索")
    private String name;
}
