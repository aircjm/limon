package com.aircjm.limon.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author aircjm
 */
public class LocalDateUtils
{
    /**
     * Date转LocalDateTime
     * @param date
     */
    public static LocalDateTime date2LocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
