package com.aircjm.limon.business.card.controller;

import com.aircjm.limon.business.card.service.AnkiCardService;
import com.aircjm.limon.business.card.vo.request.GetCardRequest;
import com.aircjm.limon.business.card.vo.request.SaveCardRequest;
import com.aircjm.limon.business.card.vo.request.SetAnkiRequest;
import com.aircjm.limon.business.card.vo.response.TaskDetailResponse;
import com.aircjm.limon.common.vo.RestResult;
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
    public RestResult refreshAllCard() {
        ankiCardService.asyncUpdateAllCard();
        return RestResult.successEmpty();
    }


    @PostMapping("/save")
    public RestResult savePage(@RequestBody @Valid SaveCardRequest request) {
        ankiCardService.saveCard(request);
        return RestResult.successEmpty();
    }

    @PostMapping("/exportCard")
    public RestResult exportCard(@RequestBody @Valid GetCardRequest request) {
       ankiCardService.exportCard(request);
        return RestResult.successEmpty();
    }


    @PostMapping("/list")
    public RestResult getCardList(@RequestBody @Valid GetCardRequest request) {
        Page<TaskDetailResponse> page = ankiCardService.getCardList(request);
        return RestResult.successData(page);
    }


    @PostMapping("/setAnki")
    public RestResult setAnki(@RequestBody @Valid SetAnkiRequest request) {
        ankiCardService.setAnki(request);
        return RestResult.successEmpty();
    }


}
