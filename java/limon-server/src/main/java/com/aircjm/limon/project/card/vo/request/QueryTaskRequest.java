package com.aircjm.limon.project.card.vo.request;

import com.aircjm.limon.project.card.domain.Task;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求获取Card数据vo
 *
 * @author aircjm
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryTaskRequest extends Page<Task> {

    private String name;

    private Integer status;
}
