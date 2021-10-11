package com.aircjm.limon.message;

/**
 * @author aircjm
 */
public interface MessageService {

    /**
     * 发送通知
     *
     * @param title   标题
     * @param context 内容
     * @param userId  id
     */
    public void sendNoticeMessage(String title, String context, Long userId);
}
