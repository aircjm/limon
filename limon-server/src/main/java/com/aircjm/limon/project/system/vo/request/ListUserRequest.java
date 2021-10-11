package com.aircjm.limon.project.system.vo.request;

import com.aircjm.limon.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author aircjm
 * 用户使用
 */
@EqualsAndHashCode(callSuper = true)
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
