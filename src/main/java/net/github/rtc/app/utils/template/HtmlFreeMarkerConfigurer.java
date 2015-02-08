package net.github.rtc.app.utils.template;

import freemarker.cache.TemplateLoader;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.List;

public class HtmlFreeMarkerConfigurer extends FreeMarkerConfigurer {

    private Logger logger = LoggerFactory.getLogger(HtmlFreeMarkerConfigurer.class);

    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
    }

    private void fixFreemarkerLogging() {
        try {
            freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_SLF4J);
            logger.info("Switched Freemarker logging to slf4j");
        } catch (ClassNotFoundException e) {
            logger.warn("Broken Freemarker logging to slf4j");
        }
    }

    @Override
    protected TemplateLoader getAggregateTemplateLoader(final List<TemplateLoader> templateLoaders) {
        logger.info("Using HtmlTemplateLoader to enforce HTML-safe content");
        return new HtmlTemplateLoader(super.getAggregateTemplateLoader(templateLoaders));
    }
}
