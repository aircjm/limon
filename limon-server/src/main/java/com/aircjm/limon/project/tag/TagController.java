package com.aircjm.limon.project.tag;


import com.aircjm.limon.project.tag.domain.Tag;
import com.aircjm.limon.project.tag.service.TagService;
import com.aircjm.limon.project.tag.vo.GetTagRequest;
import com.aircjm.limon.project.tag.vo.SaveTagRequest;
import com.aircjm.limon.project.tag.vo.response.TagDetailResponse;
import com.aircjm.limon.system.vo.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签服务
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/api/tag/")
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 新增或者更新记录
     *
     * @param request request
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Tag> save(@RequestBody @Valid SaveTagRequest request) {
        return Result.success(tagService.save(request));
    }

    /**
     * 全部列表搜索
     * @param request request
     * @return 列表数据
     */
    @PostMapping("/list")
    public Result<Page<TagDetailResponse> > allList(@RequestBody @Valid GetTagRequest request) {
        Page<TagDetailResponse> list = tagService.list(request);
        return Result.success(list);
    }
}
