package com.aircjm.project.card.service.impl;

import com.aircjm.common.utils.IdUtils;
import com.aircjm.project.card.domain.Cell;
import com.aircjm.project.card.mapper.RecordMapper;
import com.aircjm.project.card.service.RecordService;
import com.aircjm.project.card.vo.request.SaveRecordRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author aircjm
 */
@Slf4j
@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordMapper recordMapper;

    @Override
    public void save(SaveRecordRequest request) {
        Cell cell = Cell.builder()
                .title(request.getTitle())
                .desc(request.getContent())
                .type(request.getType())
                .status(request.getStatus())
                .build();
        if (Objects.nonNull(request.getId())) {
            recordMapper.updateById(cell);
        } else {
            cell.setId(IdUtils.getId());
            recordMapper.insert(cell);
        }
    }
}
