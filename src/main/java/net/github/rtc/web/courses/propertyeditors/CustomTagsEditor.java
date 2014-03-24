package net.github.rtc.web.courses.propertyeditors;

import net.github.rtc.web.courses.model.Tags;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Vladislav Pikus
 */
public class CustomTagsEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Object obj = this.getValue();
        if (obj == null) {
            return "";
        }
        //if (obj instanceof Collection<Tags>) {
            Collection<Tags> tags = ((Collection<Tags>)obj);
            StringBuffer sb = new StringBuffer();
            for(Tags tag : tags) {
                sb.append(tag.getValue()).append(",");
            }
            return sb.toString();
       // } else {
       //     return super.getAsText();
       // }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        List<String> tagsSplit = Arrays.asList(text.split(","));
        Collection<Tags> tags = new ArrayList<Tags>();
        for(String tagName : tagsSplit) {
            tags.add(new Tags(tagName));
        }
        this.setValue(tags);
    }
}