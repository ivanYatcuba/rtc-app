package net.github.rtc.app.utils.propertyeditors;



import com.google.common.collect.BiMap;

import java.beans.PropertyEditorSupport;


public class CustomClassEditor extends PropertyEditorSupport {

    private BiMap<String, Class> classMap;

    public CustomClassEditor(BiMap<String, Class> classMap) {
        this.classMap = classMap;
    }

    @Override
    public void setAsText(final String text) {
       this.setValue(classMap.get(text));
    }

    @Override
    public String getAsText() {
        return classMap.inverse().get(this.getValue());
    }
}
