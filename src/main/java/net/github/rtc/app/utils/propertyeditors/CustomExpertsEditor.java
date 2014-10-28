package net.github.rtc.app.utils.propertyeditors;

import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;

import java.beans.PropertyEditorSupport;
import java.util.*;

public class CustomExpertsEditor extends PropertyEditorSupport {

    private static final String STRING_COMMA = ",";

    private UserService userService;

    public CustomExpertsEditor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(final String text) {
        final List<String> expertsSplit = Arrays.asList(text.split(STRING_COMMA));
        final Set<User> experts = new HashSet<>();
        for (String s : expertsSplit) {
            experts.add(userService.findByCode(s));
        }
        this.setValue(experts);
    }
}
