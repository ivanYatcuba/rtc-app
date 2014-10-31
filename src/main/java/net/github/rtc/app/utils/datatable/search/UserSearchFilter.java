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

    private String status; //almost ok

    private char dateMoreLessEq; //

    public char getDateMoreLessEq() {

        return dateMoreLessEq;
    }

    public void setDateMoreLessEq(char dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Role> getAuthorities() {

        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public Date getRegisterDate() {

        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public DetachedCriteria getCriteria() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(User.class);

        if (surname != null && !("").equals(surname)) {
            criteria.add(Restrictions.ilike("surname", STRING_PROCENT + surname + STRING_PROCENT));
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

        if (status != null && !"".equals(status) && !"All".equals(status)) {
            for (UserStatus userStatus : UserStatus.values()) {
                if (userStatus.name().equals(status)) {
                    criteria.add(Restrictions.eq("status", userStatus));
                    break;
                }
            }

        }

        if (authorities != null && authorities.size() > 0) {
            if (authorities.get(0).getName() != RoleType.ALL) {
                criteria.createAlias(STRING_AUTHORITIES, STRING_AUTHORITIES);
                final Disjunction authoritiesDis = Restrictions.disjunction(); //change back to disjunction
                for (final Role role : authorities) {
                        authoritiesDis.add(Restrictions.eq("authorities.name", role.getName()));
//                    authoritiesDis.add(Restrictions.sqlRestriction("select * from User u where u.id in (select USER_ID from User_Role t where t.id = (select id from Role r where r.name = \""+role.getAuthority()+"\"))"));
                }
                criteria.add(authoritiesDis);
            }
        }

        return criteria;
    }
}


