package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.user.Role;
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

    private String surname; //ok

    private Date registerDate; //ok

    private List<Role> authorities;

    private UserStatus status; //almost ok

    private int dateMoreLessEq; //

    public void setDateMoreLessEq(int dateMoreLessEq) {
        this.dateMoreLessEq = dateMoreLessEq;
    }

    public int getDateMoreLessEq() {

        return dateMoreLessEq;
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
            if (dateMoreLessEq > 0) {
                criteria.add(Restrictions.gt("registerDate", registerDate)); }
            else
                if (dateMoreLessEq == 0) {
                criteria.add(Restrictions.eq("registerDate", registerDate));
                 }
                 else {
                   criteria.add(Restrictions.lt("registerDate", registerDate));
                 }

        }

        if (authorities != null && authorities.size() > 0) {
            criteria.createAlias(STRING_AUTHORITIES, "aut");
            final Disjunction authoritiesDis = Restrictions.disjunction();
            for (final Role role : authorities) {
                authoritiesDis.add(Restrictions.eq("authorities.name", role.getName()));
            }
            criteria.add(authoritiesDis);
        }

          return criteria;
    }
}


