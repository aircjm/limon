package com.aircjm.project.card.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

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
