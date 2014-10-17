package net.github.rtc.app.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by denis on 13.10.14.
 */
@Component("EncoderService")
public class EncoderServiceImpl implements net.github.rtc.app.service.EncoderService {
    public String encode(CharSequence rawPassword) {
        final PasswordEncoder encoder = new StandardPasswordEncoder();
        return encoder.encode(rawPassword);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        final PasswordEncoder encoder = new StandardPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
}
