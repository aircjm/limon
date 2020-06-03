package com.aircjm.project.card.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 请求保存card数据vo
 *
 * @author aircjm
 */
@Data
public class SaveCardRequest {

    @NotEmpty(message = "title(标题) 不能为空")
    private String title;

    @NotEmpty(message = "contentMd(文本内容) 不能为空")
    private String contentMd;

}
