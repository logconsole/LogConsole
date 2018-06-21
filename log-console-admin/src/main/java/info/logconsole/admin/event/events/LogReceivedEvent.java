package info.logconsole.admin.event.events;

import info.logconsole.admin.entity.LogRecordInfo;

/** 
 * @author xiahongjian
 * @time   2018-06-21 00:46:00
 */
public class LogReceivedEvent extends GenericEvent<LogRecordInfo> {
    public static final String NOTIFER_CHECK = "notiferCheck";
    
    public LogReceivedEvent(String type, LogRecordInfo log) {
        this.eventType = type;
        this.payload = log;
    }
}
