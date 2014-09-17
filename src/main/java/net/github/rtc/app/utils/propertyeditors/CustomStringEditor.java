package net.github.rtc.app.utils.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Custom tags editor for tag collection
 *
 * @author Vladislav Pikus
 */
public class CustomStringEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        final Object obj = this.getValue();
        if (obj
          == null) {
            return "";
        }
        final Collection<String> strs = ((Collection<String>) obj);
        final StringBuffer sb = new StringBuffer();
        for (final String str : strs) {
            sb.append(str).append(",");
        }
        return sb.toString();
    }

    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        final List<String> strsSplit = Arrays.asList(text.split(","));
        final Collection<String> strs = new HashSet<>();
        for (final String str : strsSplit) {
            strs.add(str);
        }
        this.setValue(strs);
    }
}
