package net.github.rtc.app.model.user;

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

    public Role(RoleType name) {
        this.name = name.name();
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
        return name;
    }
}
