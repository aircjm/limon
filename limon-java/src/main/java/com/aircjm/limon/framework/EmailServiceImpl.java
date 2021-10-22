package com.aircjm.limon.framework;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.limon.business.system.SystemConfigService;
import com.aircjm.limon.common.exception.CustomException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author aircjm
 */
@Service
public class EmailServiceImpl implements MessageService {

    @Resource
    private SystemConfigService systemConfigService;

    public MailAccount getMailAccount() {
        String mailConfig = systemConfigService.selectConfigByKey("mail.config");
        return JSONUtil.toBean(mailConfig, MailAccount.class, true);
    }


    @Override
    public void sendNoticeMessage(String title, String context, String email) {
        if (StrUtil.isEmpty(email)) {
            throw new CustomException("当前用户不存在");
        }
        MailUtil.send(getMailAccount(), email, title, context, false);
    }
}
