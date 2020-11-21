package com.aircjm.project.tag.service;

import com.aircjm.project.tag.domain.Tag;
import com.aircjm.project.tag.vo.SaveTagRequest;

public interface TagService {

    /**
     * 保存
     *
     * @param request request
     * @return tag
     */
    Tag save(SaveTagRequest request);
}
