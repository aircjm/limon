package com.aircjm.project.tag.vo;

import com.aircjm.project.tag.domain.Tag;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class GetTagRequest extends Page<Tag> {

    private String name;
}
