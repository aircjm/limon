package com.aircjm.framework.message;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.common.exception.CustomException;
import com.aircjm.common.utils.StringUtils;
import com.aircjm.project.system.domain.SysUser;
import com.aircjm.project.system.mapper.SysUserMapper;
import com.aircjm.project.system.service.ISysConfigService;
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
    private ISysConfigService sysConfigService;

    public MailAccount getMailAccount() {
        String mailConfig = sysConfigService.selectConfigByKey("mail.config");
        return JSONUtil.toBean(mailConfig, MailAccount.class, true);
    }


    @Override
    public void sendNoticeMessage(String title, String context, Long userId) {
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        if (Objects.isNull(sysUser) || StringUtils.isEmpty(sysUser.getEmail())) {
            throw new CustomException("当前用户不存在");
        }
        MailUtil.send(getMailAccount(), sysUser.getEmail(), title, context, false);
    }

}
