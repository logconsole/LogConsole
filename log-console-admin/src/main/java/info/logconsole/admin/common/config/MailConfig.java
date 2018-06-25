package info.logconsole.admin.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** 
 * @author xiahongjian
 * @time   2018-06-25 21:27:12
 */
@Component
@ConfigurationProperties(prefix = "logconsole.mail")
public class MailConfig {
    public static final String KEY_HOST = "mail.host";
    public static final String KEY_USERNAME = "mail.username";
    public static final String KEY_PASSWORD = "mail.password";
    public static final String KEY_PROTOCOL = "mail.transport.protocol";
    public static final String KEY_AUTH = "mail.smtp.auth";
    public static final String KEY_TIMEOUT = "mail.smtp.timeout";

    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
