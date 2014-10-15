package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class UserSearchFilter extends AbstractSearchCommand {
    private static final String STRING_PROCENT = "%";
    private static final String STRING_AUTHORITIES = "authorities";
    private static final String STRING_REGISTER_DATE = "registerDate"; //ok

    private String surname;

    private Date registerDate; //ok

    private List<Role> authorities;

    private UserStatus status; //almost ok

    private char dateMoreLessEq; //

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    public char getDateMoreLessEq() {

        return dateMoreLessEq;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public UserStatus getStatus() {

        return status;
    }

    public List<Role> getAuthorities() {

        return authorities;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getRegisterDate() {

        return registerDate;
    }

    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(User.class);

        if (surname != null && !("").equals(surname)) {
            criteria.add(Restrictions.ilike("surname", STRING_PROCENT + surname + STRING_PROCENT));
        }
        if (status != null) {
            criteria.add(Restrictions.eq("status", status));
        }
        if (registerDate != null) {
            switch (dateMoreLessEq) {
            case '>':
                criteria.add(Restrictions.gt(STRING_REGISTER_DATE, registerDate));
                break;
            case '=':
                criteria.add(Restrictions.eq(STRING_REGISTER_DATE, registerDate));
                break;
            case '<':
                criteria.add(Restrictions.lt(STRING_REGISTER_DATE, registerDate));
                break;
            default: break;
            }

        }

        if (authorities != null && authorities.size() > 0) {
            if (authorities.get(0).getName() != RoleType.ALL) {
                final Disjunction authoritiesDis = Restrictions.disjunction();
                for (final Role role : authorities) {
                    authoritiesDis.add(Restrictions.eq("authorities.name", role.getName()));
                }
                criteria.add(authoritiesDis);
            }
        }

        return criteria;
    }
}


