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

    @Enumerated(EnumType.STRING)
    @Column
    private RoleType name;

    public Role() {
    }

    public Role(final String strName) {
       for (RoleType role : RoleType.values()) {
           if (role.getRoleViewName().equals(strName) | role.name().equals(strName)) {
               this.name = role;
               break;
           }
       }

    }

    public Role(final RoleType name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(final RoleType name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }

    @Override
    public String toString() {
        return name.name();
    }
}
