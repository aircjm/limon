package com.aircjm.limon.common.utils;

import java.time.LocalDateTime;

/**
 * 时间工具类
 *
 * @author aircjm
 */
public class DateUtils
{


    /**
     * 判断是否是工作时间
     *
     * @param now 当前时间
     * @return 是否是工作时间
     */
    public static boolean isWorkTime(LocalDateTime now) {
        return now.getHour() > 8 && now.getHour() < 21;
    }
}
