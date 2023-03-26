package com.aircjm.limon.project.card.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * del 数据vo
 *
 * @author aircjm
 */
@Data
public class DelTaskRequest {

    @NotNull
    @Schema(name = "id")
    private Long id;
}
