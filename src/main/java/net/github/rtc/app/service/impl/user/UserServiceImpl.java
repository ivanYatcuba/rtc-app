package net.github.rtc.app.service.impl.user;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.dao.UserDao;
import net.github.rtc.app.model.entity.user.Role;
import net.github.rtc.app.model.entity.user.RoleType;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.model.entity.user.UserStatus;
import net.github.rtc.app.service.MailService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.impl.genericService.AbstractCRUDEventsService;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.utils.files.upload.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private MailService mailService;

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
    public User create(User user, MultipartFile image, boolean isActive) {
        setStatusAndImage(user, image, isActive);
        return create(user);
    }

    @Override
    public User update(User user, MultipartFile image, boolean isActive) {
        setStatusAndImage(user, image, isActive);
        return update(user);
    }

    private void setStatusAndImage(User user, MultipartFile image, boolean isActive) {
        user.setStatus(isActive ? UserStatus.ACTIVE : UserStatus.INACTIVE);
        if (!image.isEmpty()) {
            user.setPhoto(upload.saveImage(user.getCode(), image));
        }
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
    public void restoreAndDeactivateUser(String userCode) {
        restoreUser(userCode);
        inactivateUser(userCode);
    }

    @Override
    public Map<String, String> getUserNameCodeMap(RoleType roleType) {
        final Map<String, String> results = new HashMap<>();
        final List<User> users = getUserByRole(roleType);
        for (final User user : users) {
            results.put(user.shortString(), user.getCode());
        }
        return results;
    }

    @Override
    public void registerUser(User user) {
        user.setStatus(UserStatus.ACTIVE);
        user.setAuthorities(Arrays.asList(getRoleByType(RoleType.ROLE_USER)));
        user.setRegisterDate(dateService.getCurrentDate());
        create(user);
        mailService.sendRegistrationMail(user);
    }

    @Override
    public boolean userWithMailExists(String email, String currentEmail) {
        if (email.equals(currentEmail)) {
            return true;
        }
        return loadUserByUsername(email) == null;
    }
}
