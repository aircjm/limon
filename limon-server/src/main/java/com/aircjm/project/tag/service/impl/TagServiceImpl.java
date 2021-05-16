package com.aircjm.project.tag.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aircjm.common.utils.StringUtils;
import com.aircjm.project.card.domain.Task;
import com.aircjm.project.card.vo.response.TaskDetailResponse;
import com.aircjm.project.tag.domain.Tag;
import com.aircjm.project.tag.mapper.TagMapper;
import com.aircjm.project.tag.service.TagService;
import com.aircjm.project.tag.vo.GetTagRequest;
import com.aircjm.project.tag.vo.SaveTagRequest;
import com.aircjm.project.tag.vo.response.TagDetailResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        // todo 后面修改成单独操作
        saveOrUpdate(tag);
        return tag;
    }

    @Override
    public Page<TagDetailResponse> list(GetTagRequest request) {
        LambdaQueryWrapper<Tag> wrapper = new QueryWrapper<Tag>().lambda()
                .orderByDesc(Tag::getId)
                .like(StringUtils.isNotEmpty(request.getName()), Tag::getName, request.getName());
        Page<Tag> tagPage = page(request, wrapper);
        List<TagDetailResponse> taskDetailResponseList = tagPage.getRecords().stream()
                .map( e-> BeanUtil.copyProperties(e, TagDetailResponse.class))
                .collect(Collectors.toList());
        Page<TagDetailResponse> responsePage = new Page<>();
        responsePage.setTotal(tagPage.getTotal());
        responsePage.setSize(tagPage.getSize());
        responsePage.setRecords(taskDetailResponseList);
        return responsePage;
    }
}
