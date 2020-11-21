package com.aircjm.project.tag.domain;

import com.aircjm.common.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "标签")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_tag")
public class Tag extends DbBaseEntity<Tag> {

    private Long id;

    private String name;
}
