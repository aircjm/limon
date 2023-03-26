package com.aircjm.limon.project.tag.domain;

import com.aircjm.limon.system.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author aircjm
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "标签")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_tag")
public class Tag extends DbBaseEntity<Tag> {

    @Schema(name = "主键id")
    private Long id;

    @Schema(name = "标签名称")
    private String name;

    @Schema(name = "标签颜色")
    private String color;
}
