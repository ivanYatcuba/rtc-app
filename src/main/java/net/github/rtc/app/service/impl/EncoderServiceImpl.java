package net.github.rtc.app.service.impl;

import org.springframework.stereotype.Component;

@Component("EncoderService")
public class EncoderServiceImpl implements net.github.rtc.app.service.EncoderService {
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
