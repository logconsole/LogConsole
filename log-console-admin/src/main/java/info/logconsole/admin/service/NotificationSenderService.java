package info.logconsole.admin.service;

import info.logconsole.admin.entity.LogNotifer;
import info.logconsole.admin.entity.LogRecordInfo;

/** 
 * @author xiahongjian
 * @time   2018-06-25 21:21:00
 */
public interface NotificationSenderService {
    void sendNotification(LogNotifer notifer, LogRecordInfo info);
    
    void sendMailTo(String to, String subject, String text);
    
    void sendMailTo(String[] tos, String subject, String text);
}
