package net.github.rtc.app.utils.propertyeditors;

import net.github.rtc.app.model.Tag;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Custom tags editor for tag collection
 *
 * @author Vladislav Pikus
 */
public class CustomTagsEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Object obj = this.getValue();
        if (obj == null) {
            return "";
        }
        //if (obj instanceof Collection<Tag>) {
            Collection<Tag> tags = ((Collection<Tag>)obj);
            StringBuffer sb = new StringBuffer();
            for(Tag tag : tags) {
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
        Collection<Tag> tags = new ArrayList<Tag>();
        for(String tagName : tagsSplit) {
            tags.add(new Tag(tagName));
        }
        this.setValue(tags);
    }
}