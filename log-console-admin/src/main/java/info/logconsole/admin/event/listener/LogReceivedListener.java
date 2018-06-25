package info.logconsole.admin.event.listener;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import info.logconsole.admin.entity.LogRecordInfo;
import info.logconsole.admin.entity.LogWatcher;
import info.logconsole.admin.event.events.LogReceivedEvent;
import info.logconsole.admin.service.LogWatcherService;
import info.logconsole.admin.service.NotificationSenderService;

/** 
 * @author xiahongjian
 * @time   2018-06-21 00:50:41
 */
@Component
public class LogReceivedListener {
    private List<LogWatcher> watchers;
    
    @Autowired
    private LogWatcherService logWatcherService;
    
    @Autowired
    private NotificationSenderService notificationSenderService;
    
    @PostConstruct
    public void init() {
        watchers = logWatcherService.findAllEnableWatchers(true);
    }
    
    
    @Async
    @EventListener
    public void handleReceivedLog(LogReceivedEvent evt) {
        if (LogReceivedEvent.NOTIFER_CHECK.equals(evt.getEventType())) {
            checkWatchers(evt);
            return;
        }
    }
    
    private void checkWatchers(LogReceivedEvent evt) {
        LogRecordInfo info = evt.getPayload();
        watchers.forEach(watcher -> {
            if (!watcher.needHandle(info))
                return;
            watcher.getNotifers().forEach(notifer -> {
                // 未启用，则不处理
                if (!notifer.getEnable())
                    return;
                // 启用时间未到，则不处理
                if (notifer.getStartTime() != null && notifer.getStartTime().isAfter(LocalDateTime.now()))
                    return;
                // 超过启用时间，则不处理
                if (notifer.getEndTime() != null && notifer.getEndTime().isBefore(LocalDateTime.now()))
                    return;
                // 通知发送
                notificationSenderService.sendNotification(notifer, info);
            });
        });
    }
    
    
}
