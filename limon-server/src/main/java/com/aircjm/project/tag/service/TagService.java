package com.aircjm.project.tag.service;

import com.aircjm.project.tag.domain.Tag;
import com.aircjm.project.tag.vo.GetTagRequest;
import com.aircjm.project.tag.vo.SaveTagRequest;
import com.aircjm.project.tag.vo.response.TagDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface TagService {

    /**
     * 保存
     *
     * @param request request
     * @return tag
     */
    Tag save(SaveTagRequest request);

    /**
     * 列表数据展示
     * @param request 标签
     * @return 集合
     */
    Page<TagDetailResponse> list(GetTagRequest request);
}
