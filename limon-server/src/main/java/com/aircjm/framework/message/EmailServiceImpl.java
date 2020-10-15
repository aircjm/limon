package com.aircjm.framework.message;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.aircjm.common.exception.CustomException;
import com.aircjm.common.utils.StringUtils;
import com.aircjm.project.system.domain.SysUser;
import com.aircjm.project.system.mapper.SysUserMapper;
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
    private MailAccount mailAccount;


    @Override
    public void sendNoticeMessage(String title, String context, Long userId) {
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        if (Objects.isNull(sysUser) || StringUtils.isEmpty(sysUser.getEmail())) {
            throw new CustomException("当前用户不存在");
        }
        MailUtil.send(mailAccount, sysUser.getEmail(), title, context, false);
    }

}
