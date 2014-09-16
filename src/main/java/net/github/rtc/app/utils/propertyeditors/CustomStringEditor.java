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
        Object obj = this.getValue();
        if (obj == null) {
            return "";
        }
        Collection<String> strs = ((Collection<String>) obj);
        StringBuffer sb = new StringBuffer();
        for (String str : strs) {
            sb.append(str).append(",");
        }
        return sb.toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        List<String> strsSplit = Arrays.asList(text.split(","));
        Collection<String> strs = new HashSet<>();
        for (String str : strsSplit) {
            strs.add(str);
        }
        this.setValue(strs);
    }
}