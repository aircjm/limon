package com.aircjm.limon.project.card.domain;

import com.aircjm.limon.system.vo.DbBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Schema(name = "项目")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "lm_project")
public class Project extends DbBaseEntity<Project> {
    /**
     * id
     */
    @Schema(name = "id")
    private Long id;

    /**
     * 标题
     */
    @Schema(name = "name")
    private String name;

    /**
     * 内容的html
     */
    @Schema(name = "内容的html")
    private String descHtml;

    /**
     * 内容的md
     */
    @Schema(name = "内容的md")
    private String descMd;

    /**
     * 描述的json
     */
    @Schema(name = "描述的json")
    private String descJson;

    /**
     * 状态
     */
    @Schema(name = "状态")
    private Integer projectStatus;
}

