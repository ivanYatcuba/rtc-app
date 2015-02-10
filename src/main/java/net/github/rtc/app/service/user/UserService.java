package net.github.rtc.app.service.user;

import net.github.rtc.app.model.entity.user.Role;
import net.github.rtc.app.model.entity.user.RoleType;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.service.generic.GenericService;
import net.github.rtc.app.service.generic.ModelService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService extends ModelService<User>, UserDetailsService, GenericService<User> {

    void createRole(RoleType type);

    Role getRoleByType(RoleType type);

    List<User> getUserByRole(RoleType type);

    User loadUserByUsername(String email);

    User create(User user, MultipartFile image, boolean isActive);

    User update(User user, MultipartFile image, boolean isActive);

    void markUserForRemoval(String userCode);

    void restoreUser(String userCode);

    void activateUser(String userCode);

    void inactivateUser(String userCode);

    void deleteUsersMarkedForRemoval();

    void restoreAndDeactivateUser(String userCode);

    Map<String, String> getUserNameCodeMap(RoleType roleType);

    void registerUser(User user);

    boolean userWithMailExists(String email, String currentEmail);
}
