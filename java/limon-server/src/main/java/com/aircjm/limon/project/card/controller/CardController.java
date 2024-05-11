package com.aircjm.limon.project.card.controller;

import com.aircjm.limon.project.card.service.AnkiCardService;
import com.aircjm.limon.project.card.vo.request.GetCardRequest;
import com.aircjm.limon.project.card.vo.request.SaveCardRequest;
import com.aircjm.limon.project.card.vo.request.SetAnkiRequest;
import com.aircjm.limon.project.card.vo.response.TaskDetailResponse;
import com.aircjm.limon.system.vo.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


/**
 * trello对外卡片card提供接口
 *
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/api/card/")
@Tag(name = "CardController", description = "卡片实体API")
public class CardController {


    @Resource
    private AnkiCardService ankiCardService;



    @GetMapping("/refreshAllCard")
    public Result refreshAllCard() {
        ankiCardService.asyncUpdateAllCard();
        return Result.success();
    }


    @PostMapping("/save")
    public Result savePage(@RequestBody @Valid SaveCardRequest request) {
        ankiCardService.saveCard(request);
        return Result.success();
    }

    @PostMapping("/exportCard")
    public Result exportCard(@RequestBody @Valid GetCardRequest request) {
        Result ajaxResult = ankiCardService.exportCard(request);
        return Result.success(ajaxResult);
    }


    @PostMapping("/list")
    public Result getCardList(@RequestBody @Valid GetCardRequest request) {
        Page<TaskDetailResponse> page = ankiCardService.getCardList(request);
        return Result.success(page);
    }


    @PostMapping("/setAnki")
    public Result setAnki(@RequestBody @Valid SetAnkiRequest request) {
        ankiCardService.setAnki(request);
        return Result.success();
    }


}
