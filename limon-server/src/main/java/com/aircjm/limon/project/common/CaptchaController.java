package com.aircjm.limon.project.common;

import com.aircjm.limon.constant.Constants;
import com.aircjm.limon.utils.IdUtils;
import com.aircjm.limon.utils.VerifyCodeUtils;
import com.aircjm.limon.utils.sign.Base64;
import com.aircjm.limon.redis.RedisCacheService;
import com.aircjm.limon.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author aircjm
 */
@RestController
public class CaptchaController
{
    @Resource
    private RedisCacheService redisCacheService;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException
    {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = String.valueOf(IdUtils.getId());
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisCacheService.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try
        {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("uuid", uuid);
            ajax.put("img", Base64.encode(stream.toByteArray()));
            return ajax;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        finally
        {
            stream.close();
        }
    }
}
