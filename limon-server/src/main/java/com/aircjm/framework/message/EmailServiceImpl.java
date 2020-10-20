package com.aircjm.framework.message;

import com.aircjm.common.exception.CustomException;
import com.aircjm.common.utils.StringUtils;
import com.aircjm.project.system.domain.SysUser;
import com.aircjm.project.system.mapper.SysUserMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author aircjm
 */
@Service
public class EmailServiceImpl implements MessageService {

    @Resource
    private SysUserMapper sysUserMapper;


    @Resource
    private JavaMailSender customMailSender;


    @Override
    public void sendNoticeMessage(String title, String context, Long userId) {
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        if (Objects.isNull(sysUser) || StringUtils.isEmpty(sysUser.getEmail())) {
            throw new CustomException("当前用户不存在");
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(context);
        simpleMailMessage.setTo(sysUser.getEmail());
        customMailSender.send(simpleMailMessage);

    }

}
