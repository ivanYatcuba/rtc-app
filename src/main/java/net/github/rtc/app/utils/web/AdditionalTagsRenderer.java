package net.github.rtc.app.utils.web;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.servlet.support.BindStatus;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by kgavrilchenko
 */

public class AdditionalTagsRenderer {

    public String renderAdditionalTags(BindStatus br, Map<String, Object> model) {
        String attrs = "";
        final String beanName = br.getPath().substring(0, br.getPath().indexOf("."));
        final String fieldName = br.getExpression();
        final Object bean = model.get(beanName);
        if (bean != null) {
            final Class clazz = model.get(beanName).getClass();
            try {
                final Field field = clazz.getDeclaredField(fieldName);
                if (field.isAnnotationPresent(Length.class)) {
                    final Length length = field.getAnnotation(Length.class);
                    attrs = "maxlength=\"" + length.max() + "\"";
                }
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            }

        }

        return attrs;
    }

}
