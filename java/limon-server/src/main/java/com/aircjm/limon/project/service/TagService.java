package com.aircjm.limon.project.service;

import com.aircjm.limon.project.domain.Tag;
import com.aircjm.limon.project.vo.GetTagRequest;
import com.aircjm.limon.project.vo.SaveTagRequest;
import com.aircjm.limon.project.vo.response.TagDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
    Page<TagDetailResponse> page(GetTagRequest request);
}
