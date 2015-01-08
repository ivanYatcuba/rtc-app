package net.github.rtc.app.utils.freemarker;

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
        String beanName = br.getPath().substring(0, br.getPath().indexOf("."));
        String fieldName = br.getExpression();
        Object bean = model.get(beanName);
        if (bean != null) {
            Class clazz = model.get(beanName).getClass();
            try {
                Field field = clazz.getDeclaredField(fieldName);
                if (field.isAnnotationPresent(Length.class)) {
                    Length length = field.getAnnotation(Length.class);
                    attrs = "maxlength=\"" + length.max() + "\"";
                }
            } catch (NoSuchFieldException ex) {

            }

        }

        return attrs;
    }

}
