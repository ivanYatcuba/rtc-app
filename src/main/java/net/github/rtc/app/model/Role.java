package net.github.rtc.app.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author Vladislav Pikus
 */
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    public Role() {
    }

    public Role(RoleTypes name) {
        this.name = name.toString();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append("name='").append(name);
        sb.append('}');
        return sb.toString();
    }
}
