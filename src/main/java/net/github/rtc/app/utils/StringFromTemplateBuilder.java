package net.github.rtc.app.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Component
public class StringFromTemplateBuilder {

    private static final String UNKNOWN_VALUE = "UNKNOWN VALUE";
    private static final String ILLEGAL_ARGUMENT = "Error using argument ";

    private Template template;
    private Map<String, Object> templateParams;

    private final Configuration config = new Configuration();

    public StringFromTemplateBuilder setTemplate(String templatePath) {
        try {
            config.setClassForTemplateLoading(this.getClass(), "/");
            this.template = config.getTemplate(templatePath);
        } catch (IOException e) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT + templatePath);
        }
        return this;
    }

    public StringFromTemplateBuilder setTemplateParams(Map<String, Object> templateParams) {
        if (templateParams == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }
        this.templateParams = templateParams;
        return this;
    }

    public String build() {
        try {
            final StringWriter writer = new StringWriter();
            template.process(templateParams, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            return UNKNOWN_VALUE;
        }
    }
}
