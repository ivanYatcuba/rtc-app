package net.github.rtc.app.service;

import net.github.rtc.app.model.User;

/**
 * Created by Ivan Yatcuba on 7/22/14.
 */
public interface UserServiceLogin {
    public User loadUserByUsername(String email);
}
