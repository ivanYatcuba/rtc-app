package net.github.rtc.app.service.impl.user;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.UserDao;
import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.model.user.UserStatus;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.impl.genericservise.AbstractCRUDEventsService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.files.upload.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractCRUDEventsService<User> implements UserService {

    private static final int USER_REMOVAL_DELAY = 3;
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());
    @Autowired
    private UserDao userDao;
    @Autowired
    private DateService dateService;
    @Autowired
    private FileUpload upload;

    @Override
    protected GenericDao<User> getDao() {
        return userDao;
    }

    @Override
    public User create(final User user) {
        user.setRegisterDate(dateService.getCurrentDate());
        user.setCode(getCode());
        super.create(user);
        return user;
    }

    @Override
    public User create(User user, MultipartFile image) {
        //set photo
        if (!image.isEmpty()) {
            user.setPhoto(upload.saveImage(user.getCode(), image));
        }
        return create(user);
    }

    @Override
    public Class<User> getType() {
        return User.class;
    }

    @Override
    @Transactional
    public void createRole(final RoleType type) {
        log.debug("Creating user role with type: {}", type);
        userDao.createRole(type);
    }

    @Override
    @Transactional
    public Role getRoleByType(final RoleType type) {
        log.debug("Getting user role with type: {}", type);
        return userDao.getRoleByType(type);
    }

    @Override
    @Transactional
    public List<User> getUserByRole(final RoleType type) {
        log.debug("Getting user list with type: {}", type);
        return userDao.getUsersByType(type);
    }

    @Override
    @Transactional
    public User loadUserByUsername(final String email) {
        log.debug("Loading user with email: {}", email);
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional
    public void markUserForRemoval(String userCode) {
        final User user = findByCode(userCode);
        user.setStatus(UserStatus.FOR_REMOVAL);
        user.setRemovalDate(dateService.addDays(dateService.getCurrentDate(), USER_REMOVAL_DELAY));
        update(user);
    }

    @Override
    @Transactional
    public void activateUser(String userCode) {
        final User user = findByCode(userCode);
        user.setStatus(UserStatus.ACTIVE);
        super.update(user);
    }

    @Override
    @Transactional
    public void inactivateUser(String userCode) {
        final User user = findByCode(userCode);
        user.setStatus(UserStatus.INACTIVE);
        super.update(user);
    }

    @Override
    @Transactional
    public void restoreUser(String userCode) {
        final User user = findByCode(userCode);
        user.setRemovalDate(null);
        super.update(user);
    }

    @Override
    @Transactional
    public void deleteUsersMarkedForRemoval() {
        userDao.deletingUser();
    }

    @Override
    public User getAuthorizedUser() {
        return loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
