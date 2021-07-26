package com.aircjm.project.card.vo.request;

import com.aircjm.project.card.constants.TaskStatusEnum;
import com.aircjm.project.card.domain.Task;
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
