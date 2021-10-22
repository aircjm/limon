package com.aircjm.limon.business.tag.vo;

import com.aircjm.limon.business.tag.domain.Tag;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * @author aircjm
 */
@ApiModel(description = "查询标签列表数据请求参数")
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class GetTagRequest extends Page<Tag> {

    @ApiModelProperty(value = "标签名称 支持模糊搜索")
    private String name;
}
