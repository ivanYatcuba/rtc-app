package net.github.rtc.app.dao.user;

import net.github.rtc.app.dao.generic.AbstractGenericDaoImpl;
import net.github.rtc.app.exception.ServiceProcessingException;
import net.github.rtc.app.model.entity.user.Role;
import net.github.rtc.app.model.entity.user.RoleType;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.utils.web.files.upload.FileUpload;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


/**
 * @author Саша
 */
@Repository
public class UserDaoImpl extends AbstractGenericDaoImpl<User> implements UserDao {

    public static final String EMAIL_STRING = "email";
    @Autowired
    private FileUpload upload;

    @Override
    public User findByEmail(final String email) {
        return (User) getCurrentSession().createCriteria(User.class).add(Restrictions.eq(EMAIL_STRING, email)).uniqueResult();
    }

    @Override
    public boolean isEmailExist(String email) {
        final int count = ((Long) getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq(EMAIL_STRING, email))
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
        return count != 0;
    }

    @Override
    public Role getRoleByType(final RoleType type) {
        return (Role) getCurrentSession().createCriteria(Role.class).add(Restrictions.eq("name", type)).uniqueResult();
    }

    @Override
    public Role createRole(final RoleType type) {
        final Role newRole = new Role(type);
        getCurrentSession().save(newRole);
        return newRole;
    }

    @Override
    public List<User> getUsersByType(final RoleType type) {
        return getCurrentSession().createCriteria(User.class).createAlias("authorities", "a").add(Restrictions.eq("a.name", type)).list();
    }

    @Override
    public void deletingUser() {
        final Session session = getCurrentSession();
        final Collection<User> listUser = session.createQuery("from User u where u.removalDate is not null").list();
        for (final User user : listUser) {
            try {
                upload.deletePhoto(user.getCode());
            } catch (Exception e) {
                throw new ServiceProcessingException("Unable to save image: " + e.getMessage());
            }
            session.delete(user);
        }
    }

}
