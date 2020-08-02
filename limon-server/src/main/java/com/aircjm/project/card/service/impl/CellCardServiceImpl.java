package com.aircjm.project.card.service.impl;

import com.aircjm.common.exception.CustomException;
import com.aircjm.common.utils.StringUtils;
import com.aircjm.common.utils.bean.BeanUtils;
import com.aircjm.common.utils.poi.ExcelUtil;
import com.aircjm.framework.web.domain.AjaxResult;
import com.aircjm.project.anki.response.AnkiRespVo;
import com.aircjm.project.card.domain.Cell;
import com.aircjm.project.card.domain.TrelloCard;
import com.aircjm.project.card.mapper.CellCardMapper;
import com.aircjm.project.card.service.AnkiService;
import com.aircjm.project.card.service.CellCardService;
import com.aircjm.project.card.service.TrelloCardService;
import com.aircjm.project.card.vo.anki.vo.Fields;
import com.aircjm.project.card.vo.anki.vo.Note;
import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import com.aircjm.project.card.vo.request.SetAnkiRequest;
import com.aircjm.project.card.vo.response.CellCardDetailResponse;
import com.aircjm.project.system.service.ISysConfigService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 卡片服务
 *
 * @author aircjm
 */
@Service
@Slf4j
public class CellCardServiceImpl extends ServiceImpl<CellCardMapper, Cell> implements CellCardService {

    @Resource
    Trello trello;



    @Resource
    private AnkiService ankiService;



    @Override
    public void saveCard(SaveCardRequest request) {
    }

    @Override
    public Page<CellCardDetailResponse> getCardList(GetCardRequest request) {
        QueryWrapper<Cell> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(request.getStatus())) {
            queryWrapper.eq("status", request.getStatus());
        }
        Page<Cell> boardCards = page(request, queryWrapper);
        Page<CellCardDetailResponse> response = new Page<>();
        BeanUtils.copyProperties(boardCards, response);
        response.setRecords(boardCards.getRecords().stream().map(item -> {
            CellCardDetailResponse detail = new CellCardDetailResponse();
            BeanUtils.copyProperties(item, detail);
            return detail;
        }).collect(Collectors.toList()));
        return response;
    }

    @Override
    public AjaxResult exportCard(GetCardRequest request) {
        Page<CellCardDetailResponse> page = getCardList(request);
        ExcelUtil<CellCardDetailResponse> util = new ExcelUtil<>(CellCardDetailResponse.class);
        AjaxResult ajaxResult = util.exportExcel(page.getRecords(), "卡片集合");

        return ajaxResult;
    }




    @Override
    public void setAnki(SetAnkiRequest request) {
        QueryWrapper<Cell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("card_id", request.getCardId());
        Cell one = getOne(queryWrapper);
        log.info("获取的卡片为：{}", one.getCardTitle());

        // 开始生成卡片
        Card card = trello.getCard(request.getCardId());
        Note note = convert(card);
        log.info("请求生成anki卡片请求参数是：{}", JSON.toJSONString(note));
        log.info("请求生成anki desc：{}", card.getDesc());
        AnkiRespVo ankiRespVo = ankiService.addNote(note);
        if (StringUtils.isNotEmpty(ankiRespVo.getError())) {
            throw new CustomException("生成Anki的Note失败，失败原因为：" + ankiRespVo.getError());
        }
        UpdateWrapper<Cell> updateWrapper = new UpdateWrapper<>();
        update(updateWrapper);
    }


    private static Note convert(com.julienvey.trello.domain.Card card) {
        Note note = new Note();
        note.setDeckName("trello");
        note.setModelName("Markdown");

        Fields fields = new Fields();
        fields.setFront(card.getName());
        fields.setBack(card.getDesc().replace("\n", "<br>"));
        note.setFields(fields);
        if (CollectionUtils.isNotEmpty(card.getLabels())) {
            note.setTags(card.getLabels().stream().map(Label::getName).collect(Collectors.toList()));
        }

        return note;
    }

}
