package net.github.rtc.app.utils.propertyeditors;

import net.github.rtc.app.model.user.Role;
import net.github.rtc.app.model.user.RoleType;
import net.github.rtc.app.service.user.UserService;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by denis on 20.10.14.
 */
public class CustomRoleEditor extends PropertyEditorSupport {

    private static final String COMMA = ",";

    private UserService userService;

    public CustomRoleEditor(UserService userService) {
       this.userService = userService;
    }

    @Override
    public void setAsText(final String text) {
        final List<String> rolesSplit = Arrays.asList(text.split(COMMA));
        final Collection<Role> roles = new ArrayList<>();
        if (!rolesSplit.get(0).isEmpty()) {
            for (final String roleName : rolesSplit) {
               roles.add(userService.getRoleByType(RoleType.getTypeByString(roleName)));
            }
        }
        this.setValue(roles);
    }

}
