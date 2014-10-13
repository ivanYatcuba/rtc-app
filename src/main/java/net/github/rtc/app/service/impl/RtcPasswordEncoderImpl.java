package net.github.rtc.app.service.impl;

import net.github.rtc.app.service.RtcPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * Created by denis on 13.10.14.
 */
@Repository
public class RtcPasswordEncoderImpl implements RtcPasswordEncoder {
    public String encode(CharSequence rawPassword) {
        final PasswordEncoder encoder = new StandardPasswordEncoder();
        return encoder.encode(rawPassword);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        final PasswordEncoder encoder = new StandardPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
}
