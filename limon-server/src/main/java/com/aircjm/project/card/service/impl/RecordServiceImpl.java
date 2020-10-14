package com.aircjm.project.card.service.impl;

import com.aircjm.common.utils.DateUtils;
import com.aircjm.common.utils.IdUtils;
import com.aircjm.project.card.domain.Cell;
import com.aircjm.project.card.mapper.RecordMapper;
import com.aircjm.project.card.service.RecordService;
import com.aircjm.project.card.vo.request.SaveRecordRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author aircjm
 */
@Slf4j
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Cell> implements RecordService {

    @Override
    public void save(SaveRecordRequest request) {
        Cell cell = Cell.builder()
                .title(request.getTitle())
                .cellDesc(request.getContent())
                .cellHtml(request.getContent())
                .type(request.getType())
                .status(request.getStatus())
                .build();
        if (Objects.isNull(request.getId())) {
            cell.setId(IdUtils.getId());
            cell.setCreateTime(LocalDateTime.now());
        }

        saveOrUpdate(cell);
    }

    @Override
    public void noticeAdd() {
        QueryWrapper<Cell> queryWrapper = new QueryWrapper<>();
        LocalDateTime now = LocalDateTime.now();
        queryWrapper.ge("update_time", now.plusMinutes(-30L));
        Cell one = getOne(queryWrapper);
        if (Objects.isNull(one) && DateUtils.isWorkTime(now)) {
            // 消息通知
        }
    }
}
