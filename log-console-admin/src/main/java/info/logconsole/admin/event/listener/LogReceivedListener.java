package info.logconsole.admin.event.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import info.logconsole.admin.entity.LogWatcher;
import info.logconsole.admin.event.events.LogReceivedEvent;

/** 
 * @author xiahongjian
 * @time   2018-06-21 00:50:41
 */
@Component
public class LogReceivedListener {
    private Map<Integer, LogWatcher> watchers;
    
    @PostConstruct
    public void init() {
        watchers = new ConcurrentHashMap<>();
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
        
    }
}
