package com.aircjm.limon.project.service.impl;

import cn.hutool.core.bean.BeanUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.aircjm.limon.project.domain.Tag;
import com.aircjm.limon.project.mapper.TagMapper;
import com.aircjm.limon.project.service.TagService;
import com.aircjm.limon.project.vo.GetTagRequest;
import com.aircjm.limon.project.vo.SaveTagRequest;
import com.aircjm.limon.project.vo.response.TagDetailResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author aircjm
 */
@Slf4j
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag save(SaveTagRequest request) {
        Tag tag = BeanUtil.copyProperties(request, Tag.class);
        if (Objects.isNull(tag.getId())) {
            tag.setCreateTime(new Date());
        }
        tagMapper.insert(tag);
        return tag;
    }

    @Override
    public Page<TagDetailResponse> page(GetTagRequest request) {
//        LambdaQueryWrapper<Tag> wrapper = new QueryWrapper<Tag>().lambda()
//                .orderByDesc(Tag::getId)
//                .like(StrUtil.isNotEmpty(request.getName()), Tag::getName, request.getName());
//        Page<Tag> tagPage = page(request, wrapper);
//        List<TagDetailResponse> taskDetailResponseList = tagPage.getRecords().stream()
//                .map( e-> BeanUtil.copyProperties(e, TagDetailResponse.class))
//                .collect(Collectors.toList());
//        Page<TagDetailResponse> responsePage = new Page<>();
//        responsePage.setTotal(tagPage.getTotal());
//        responsePage.setSize(tagPage.getSize());
//        responsePage.setRecords(taskDetailResponseList);
        return null;
    }
}
