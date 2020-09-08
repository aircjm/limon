package com.aircjm.project.mail.service.impl;

import cn.hutool.extra.mail.MailUtil;
import com.aircjm.project.mail.service.MailService;
import com.aircjm.project.mail.vo.SendMailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 邮件service
 *
 * @author aircjm
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Override
    public void sendMail(SendMailRequest request) {
        try {
            MailUtil.send(request.getSender(), request.getSubject(), request.getHtml(), true, (File) null);
        } catch (Exception e) {
            log.error("发送邮件异常");
            e.printStackTrace();
        }
    }
}
