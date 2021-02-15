package com.aircjm.project.tag.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aircjm.project.tag.domain.Tag;
import com.aircjm.project.tag.mapper.TagMapper;
import com.aircjm.project.tag.service.TagService;
import com.aircjm.project.tag.vo.GetTagRequest;
import com.aircjm.project.tag.vo.SaveTagRequest;
import com.aircjm.project.tag.vo.response.TagDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public Tag save(SaveTagRequest request) {
        Tag tag = BeanUtil.copyProperties(request, Tag.class);
        if (Objects.isNull(tag.getId())) {
            tag.setCreateTime(LocalDateTime.now());
        }
        saveOrUpdate(tag);
        return tag;
    }

    @Override
    public List<TagDetailResponse> list(GetTagRequest request) {
        List<Tag> list = list();
        return list.stream().map(item -> {
            return BeanUtil.copyProperties(item, TagDetailResponse.class);
        }).collect(Collectors.toList());
    }
}
