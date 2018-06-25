package info.logconsole.admin.util;
/** 
 * @author xiahongjian
 * @time   2018-06-25 22:05:25
 */

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerUtils.class);
    private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
    
    static {
        cfg.setDefaultEncoding("UTF-8");
        cfg.setDateFormat("yyyy-MM-dd");
        cfg.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 处理FreeMarker模板，并以字符串形式返回
     * @param template
     * @param model
     * @return
     */
    public static String process(String template, Object model) {
        StringWriter result = new StringWriter();
        try {
            Template t = new Template("mail_template", new StringReader(template), cfg);
            t.process(model, result);
        } catch (IOException | TemplateException e) {
            LOGGER.warn("Failed to process FreeMarker template.", e);
        }
        return result.toString();
    }
}
