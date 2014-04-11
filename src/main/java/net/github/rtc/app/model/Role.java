package net.github.rtc.app.model;

import org.springframework.security.core.GrantedAuthority;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vladislav Pikus
 */
@XmlRootElement
public class Role implements GrantedAuthority {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
