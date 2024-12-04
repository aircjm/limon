package com.aircjm.limon.system.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IdVo {

    @NotNull
    private Long id;
}
