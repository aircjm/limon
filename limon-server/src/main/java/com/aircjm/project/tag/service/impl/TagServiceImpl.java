package com.aircjm.project.tag.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aircjm.project.tag.domain.Tag;
import com.aircjm.project.tag.mapper.TagMapper;
import com.aircjm.project.tag.service.TagService;
import com.aircjm.project.tag.vo.SaveTagRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;


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
}
