package com.aircjm.limon.security;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录对象
 * 
 * @author aircjm
 */
public class LoginBody
{
    /**
     * 用户名
     */
    @NotBlank
    private String username;

    /**
     * 用户密码
     */
    @NotBlank
    private String password;

    /**
     * 验证码
     */
    @NotBlank
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
}
