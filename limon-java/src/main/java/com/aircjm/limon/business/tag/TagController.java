package com.aircjm.limon.business.tag;


import com.aircjm.limon.business.BaseController;
import com.aircjm.limon.business.tag.domain.Tag;
import com.aircjm.limon.business.tag.service.TagService;
import com.aircjm.limon.business.tag.vo.GetTagRequest;
import com.aircjm.limon.business.tag.vo.SaveTagRequest;
import com.aircjm.limon.business.tag.vo.response.TagDetailResponse;
import com.aircjm.limon.common.vo.RestResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 标签服务
 *
 * @author aircjm
 */
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
    public RestResult<Tag> save(@RequestBody @Valid SaveTagRequest request) {
        return RestResult.successData(tagService.save(request));
    }

    /**
     * 全部列表搜索
     *
     * @param request request
     * @return 列表数据
     */
    @PostMapping("/list")
    public RestResult<Page<TagDetailResponse>> allList(@RequestBody @Valid GetTagRequest request) {
        Page<TagDetailResponse> list = tagService.list(request);
        return RestResult.successData(list);
    }
}
