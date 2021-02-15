package com.aircjm.project.tag;


import com.aircjm.common.vo.RestResponse;
import com.aircjm.framework.web.controller.BaseController;
import com.aircjm.project.card.vo.request.GetCardRequest;
import com.aircjm.project.card.vo.response.TaskDetailResponse;
import com.aircjm.project.tag.domain.Tag;
import com.aircjm.project.tag.service.TagService;
import com.aircjm.project.tag.vo.GetTagRequest;
import com.aircjm.project.tag.vo.SaveTagRequest;
import com.aircjm.project.tag.vo.response.TagDetailResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tag/")
public class TagController extends BaseController {

    @Resource
    private TagService tagService;

    /**
     * 新增或者更新记录
     *
     * @param request request
     * @return 结果
     */
    @PostMapping("/save")
    public RestResponse<Tag> save(@RequestBody @Valid SaveTagRequest request) {
        return RestResponse.successData(tagService.save(request));
    }

    /**
     * 列表搜索
     * @param request request
     * @return 列表数据
     */
    @PostMapping("/allList")
    public RestResponse allList(@RequestBody @Valid GetTagRequest request) {
        List<TagDetailResponse> page = tagService.list(request);
        return RestResponse.successData(page);
    }
}
