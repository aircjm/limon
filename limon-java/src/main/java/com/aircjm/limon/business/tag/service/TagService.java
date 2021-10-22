package com.aircjm.limon.business.tag.service;

import com.aircjm.limon.business.tag.domain.Tag;
import com.aircjm.limon.business.tag.vo.GetTagRequest;
import com.aircjm.limon.business.tag.vo.SaveTagRequest;
import com.aircjm.limon.business.tag.vo.response.TagDetailResponse;
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
    Page<TagDetailResponse> list(GetTagRequest request);
}
