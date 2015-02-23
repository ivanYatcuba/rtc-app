package net.github.rtc.app.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class TemplateStringBuilder {

    public static final String UNKNOWN_VALUE = "UNKNOWN VALUE";

    public String build(String templatePath) {
        return build(templatePath, new HashMap<String, Object>());
    }

    public String build(String templatePath, Map<String, Object> templateParams) {
        try {
            final Configuration config = new Configuration();
            config.setClassForTemplateLoading(this.getClass(), "/");
            final Template template = config.getTemplate(templatePath);

            final StringWriter writer = new StringWriter();
            if (templateParams != null) {
                template.process(templateParams, writer);
            }

            return writer.toString();
        } catch (IOException | TemplateException e) {
            return UNKNOWN_VALUE;
        }
    }


}
