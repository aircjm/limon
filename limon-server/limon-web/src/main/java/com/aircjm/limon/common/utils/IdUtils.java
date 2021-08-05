package com.aircjm.limon.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * ID生成器工具类
 *
 * @author aircjm
 */
public class IdUtils
{

    private static final Snowflake SNOWFLAKE = IdUtil.getSnowflake(1, 1);

    public static Long getId() {
        return SNOWFLAKE.nextId();
    }

    public static String getIdStr() {
        return SNOWFLAKE.nextIdStr();
    }
}
