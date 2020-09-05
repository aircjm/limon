package com.aircjm.project.mail.service.impl;

import com.aircjm.project.mail.service.MailService;
import com.aircjm.project.mail.vo.SendMailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * 邮件service
 *
 * @author aircjm
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Resource
    JavaMailSender javaMailSender;


    @Override
    public void sendMail(SendMailRequest request) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setTo(request.getReceiver());
            mimeMessageHelper.setFrom(request.getSender());
            mimeMessageHelper.setSubject(request.getSubject());
            mimeMessageHelper.setText(request.getHtml(), true);
        } catch (Exception e) {
            log.error("发送邮件异常");
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
