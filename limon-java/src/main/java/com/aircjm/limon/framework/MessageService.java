package com.aircjm.limon.framework;

/**
 * @author aircjm
 */
public interface MessageService {

    /**
     * 发送通知
     *
     * @param title   标题
     * @param context 内容
     * @param email  email
     */
    public void sendNoticeMessage(String title, String context, String email);
}
