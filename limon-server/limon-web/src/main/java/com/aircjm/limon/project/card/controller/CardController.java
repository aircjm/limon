package com.aircjm.limon.project.card.controller;

import com.aircjm.limon.common.vo.RestResponse;
import com.aircjm.limon.framework.aspectj.lang.annotation.Log;
import com.aircjm.limon.framework.aspectj.lang.enums.BusinessType;
import com.aircjm.limon.framework.web.domain.AjaxResult;
import com.aircjm.limon.project.card.service.AnkiCardService;
import com.aircjm.limon.project.card.vo.request.GetCardRequest;
import com.aircjm.limon.project.card.vo.request.SaveCardRequest;
import com.aircjm.limon.project.card.vo.request.SetAnkiRequest;
import com.aircjm.limon.project.card.vo.response.TaskDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * trello对外卡片card提供接口
 *
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/api/card/")
@Api(value = "CardController", description = "卡片实体API")
public class CardController {


    @Resource
    private AnkiCardService ankiCardService;



    @GetMapping("/refreshAllCard")
    public RestResponse refreshAllCard() {
        ankiCardService.asyncUpdateAllCard();
        return RestResponse.successEmpty();
    }


    @PostMapping("/save")
    public RestResponse savePage(@RequestBody @Valid SaveCardRequest request) {
        ankiCardService.saveCard(request);
        return RestResponse.successEmpty();
    }

    @PostMapping("/exportCard")
    public RestResponse exportCard(@RequestBody @Valid GetCardRequest request) {
        AjaxResult ajaxResult = ankiCardService.exportCard(request);
        return RestResponse.successData(ajaxResult);
    }


    @PostMapping("/list")
    public RestResponse getCardList(@RequestBody @Valid GetCardRequest request) {
        Page<TaskDetailResponse> page = ankiCardService.getCardList(request);
        return RestResponse.successData(page);
    }


    @PostMapping("/setAnki")
    public RestResponse setAnki(@RequestBody @Valid SetAnkiRequest request) {
        ankiCardService.setAnki(request);
        return RestResponse.successEmpty();
    }


}
