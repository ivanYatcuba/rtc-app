package net.github.rtc.app.model;


import org.hibernate.validator.constraints.NotBlank;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Vladislav Pikus
 */
@XmlRootElement
public class Tag implements Serializable {

    @NotBlank
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Tag(String value) {
        this.value = value;
    }

    public Tag() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (value != null ? !value.equals(tag.value) : tag.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tag{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
