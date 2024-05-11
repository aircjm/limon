package com.aircjm.limon.project.card.vo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 设置卡片的集合
 *
 * @author aircjm
 */
@Data
public class SetAnkiRequest {

    @NotNull(message = "卡片id")
    private String cardId;

}
