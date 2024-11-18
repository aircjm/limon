package com.aircjm.limon.project.mapper;

import com.aircjm.limon.project.domain.Tag;
import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    Tag selectByPrimaryKey(Long id);

    List<Tag> selectAll();

    int updateByPrimaryKey(Tag record);
}