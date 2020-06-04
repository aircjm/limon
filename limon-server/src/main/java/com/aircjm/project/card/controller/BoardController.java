package com.aircjm.project.card.controller;

import com.aircjm.common.vo.RestResponse;
import com.aircjm.project.card.service.CardService;
import com.aircjm.project.card.vo.request.SaveCardRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * trello对外卡片card提供接口
 *
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/api/board/")
@Api(value = "BoardController", description = "卡片博客API")
public class BoardController {


    @Resource
    private CardService cardService;

    @PostMapping("/save")
    public RestResponse savePage(@RequestBody @Valid SaveCardRequest request) {
        cardService.saveCard(request);
        return RestResponse.successEmpty();
    }
}
