package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.UserDao;
import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.ModelService;
import net.github.rtc.app.service.UserService;
import net.github.rtc.app.utils.datatable.SearchResults;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Sasha
 */
@Service("userService")
public class UserServiceImpl implements ModelService<User>, UserService {

    public static final int USER_REMOVAL_DELY = 3;
    private static Logger log = LoggerFactory.getLogger(
      UserServiceImpl.class.getName());
    private static final String REMOVING_USER_WITH_CODE =
            "Removing user  with code: ";
    private static final String GETTING_USER = "Getting user: ";

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<User> findAll() {
        log.debug("Loading all users from database...");
        return (List) userDao.findAll();
    }


    @Override
    @Transactional
    public void delete(final User user) {
        log.debug("Removing user  with email: "
                + user.getEmail());
        userDao.deleteByCode(user.getCode());
    }

    @Override
    @Transactional
    public void deleteByCode(final String code) {
        log.debug(REMOVING_USER_WITH_CODE
                + code);
        userDao.deleteByCode(code);
    }

    @Override
    @Transactional
    public User findByCode(final String code) {
        log.debug(REMOVING_USER_WITH_CODE
                + code);
        return userDao.findByCode(code);
    }

    @Override
    @Transactional
    public User create(final User user) {
        log.debug("Creating user: "
                + user);
        user.setCode(UUID.randomUUID().toString());
        final PasswordEncoder encoder = new StandardPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.create(user);
    }

    @Override
    @Transactional
    public void update(final User user) {
        log.debug("Updating user: "
                + user);
        final PasswordEncoder encoder = new StandardPasswordEncoder();
        final User userToUpdate = userDao.findByCode(user.getCode());
        if (user.getPassword()
          != userToUpdate.getPassword()) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        userDao.update(user);
    }

    @Override
    public Class<User> getType() {
        return User.class;
    }

    @Override
    @Transactional
    public void createRole(final RoleType type) {
        log.debug("Creating user role with type: "
                + type);
        userDao.createRole(type);
    }

    @Override
    @Transactional
    public Role getRoleByType(final RoleType type) {
        log.debug("Getting user role with type: "
                + type);
        return userDao.getRoleByType(type);
    }

    @Override
    @Transactional
    public List<User> getUserByRole(final RoleType type) {
        log.debug("Getting user list with type: "
                + type);
        return userDao.getUserByType(type);
    }

    @Override
    @Transactional
    public SearchResults<User> search(
      final DetachedCriteria criteria, final int start, final int max) {
        return userDao.search(criteria, start, max);
    }

    @Override
    @Transactional
    public void markUserForRemoval(final User user) {
        if (user.getStatus()
          == UserStatus.ACTIVE) {
            user.setStatus(UserStatus.FOR_REMOVAL);
            user.setRemovalDate(new DateTime(new Date()).plusDays(
              USER_REMOVAL_DELY).toDate());
            log.debug("Getting user before update: "
                    + user);
            userDao.update(user);
            log.debug("Getting user after update: "
                    + user);
        } else {
            user.setStatus(UserStatus.ACTIVE);
            user.setRemovalDate(null);
            log.debug(GETTING_USER
                    + user);
            userDao.update(user);
            log.debug(GETTING_USER
                    + user);
        }
    }

    @Override
    @Transactional
    public void markUserForRemoval(final String userCode) {
        log.debug("Getting code: "
                + userCode);
        final User user = findByCode(userCode);
        log.debug("User: "
                + user);
        markUserForRemoval(user);
    }
}
