package com.aircjm.project.system.vo.request;

import com.aircjm.framework.web.page.PageDomain;
import lombok.Data;

@Data
public class ListUserRequest extends PageDomain {


    /**
     * 用户账号
     */
    private String userName;


    /**
     * 手机号码
     */
    private String phonenumber;


    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;
}
