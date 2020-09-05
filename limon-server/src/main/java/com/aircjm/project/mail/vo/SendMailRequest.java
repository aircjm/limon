package com.aircjm.project.mail.vo;

import lombok.Data;

/**
 * 发送邮件请求参数
 *
 * @author aircjm
 */
@Data
public class SendMailRequest {

    /**
     * 发送者
     */
    private String sender;

    /**
     * 接收者
     */
    private String receiver;

    /**
     * 主题
     */
    private String subject;


    /**
     * 具体内容
     */
    private String html;

}
