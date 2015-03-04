package net.github.rtc.app.utils.web.template;

import freemarker.cache.TemplateLoader;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.List;

/**
 * Configuration that uses custom template loaders
 * @see net.github.rtc.app.utils.web.template.HtmlTemplateLoader
 */
public class HtmlFreeMarkerConfigurer extends FreeMarkerConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlFreeMarkerConfigurer.class);

    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
    }

    @Override
    protected TemplateLoader getAggregateTemplateLoader(final List<TemplateLoader> templateLoaders) {
        LOG.info("Using HtmlTemplateLoader to enforce HTML-safe content");
        return new HtmlTemplateLoader(super.getAggregateTemplateLoader(templateLoaders));
    }
}
