package com.aircjm.project.mail.service;


import com.aircjm.project.mail.vo.SendMailRequest;

/**
 * 邮件服务
 *
 * @author aircjm
 */
public interface MailService {


    void sendMail(SendMailRequest request);
}
