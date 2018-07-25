package info.logconsole.admin.service.impl;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import info.logconsole.admin.common.config.MailConfig;
import info.logconsole.admin.entity.LogNotifer;
import info.logconsole.admin.entity.LogRecordInfo;
import info.logconsole.admin.entity.enums.NotiferType;
import info.logconsole.admin.service.NotificationSenderService;
import info.logconsole.admin.util.FreeMarkerUtils;

/**
 * @author xiahongjian
 * @time 2018-06-25 21:22:55
 */
@Service
public class NotificationSenderServiceImpl implements NotificationSenderService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(NotificationSenderServiceImpl.class);
    
    @Autowired
    private MailConfig mailConfig;
    private JavaMailSender mailSender;
    
    @PostConstruct
    public void init() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setDefaultEncoding("UTF-8");
        sender.setUsername(mailConfig.getUsername());
        sender.setPassword(mailConfig.getPassword());
        Properties prop = new Properties();
        prop.put(MailConfig.KEY_AUTH, true);
        prop.put(MailConfig.KEY_PROTOCOL, "smtp");
        prop.put(MailConfig.KEY_TIMEOUT, "25000");
        if (mailConfig.getUsername() == null || mailConfig.getPassword() == null) {
            LOGGER.warn("No mail config, place check config file.");
        } else {
            prop.put(MailConfig.KEY_USERNAME, mailConfig.getUsername());
            prop.put(MailConfig.KEY_PASSWORD, mailConfig.getPassword());
        }
        sender.setJavaMailProperties(prop);
    }

    @Override
    public void sendNotification(LogNotifer notifer, LogRecordInfo info) {
        if (NotiferType.EMAIL == notifer.getType()) {
            Set<String> receivers = notifer.getAllReceivers();
            sendMailTo(receivers.toArray(new String[receivers.size()]), notifer.getSubject(),
                    FreeMarkerUtils.process(notifer.getTemplate(), buildModel(info)));
        }
    }

    private Map<String, Object> buildModel(LogRecordInfo info) {
        Map<String, Object> model = Maps.newHashMap();
        model.put("appName", info.getAppName());
        model.put("host", info.getHost());
        model.put("clazz", info.getClazz());
        model.put("log", info.getContent());
        model.put("logLevel", info.getLogLevel());
        model.put("logTime", info.getLogTime());
        return model;
    }

    @Override
    public void sendMailTo(String to, String subject, String text) {
        sendMailTo(new String[] {to}, subject, text);
    }

    @Override
    public void sendMailTo(String[] tos, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(tos);
        message.setText(text);
        message.setSubject(subject);
        // 如果不设置或者设置的发送者与配置的发送邮箱不一致，将会发送失败
        message.setFrom(mailConfig.getUsername());
        mailSender.send(message);
    }

}
