package net.github.rtc.app.service;

import net.github.rtc.app.model.user.User;

/**
 * Created by Ivan Yatcuba on 7/22/14.
 */
public interface UserServiceLogin {
    User loadUserByUsername(String email);
}
