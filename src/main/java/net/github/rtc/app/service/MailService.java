package net.github.rtc.app.service;


import net.github.rtc.app.model.entity.user.User;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by denis on 21.11.14.
 */
public interface MailService {
    /**
     *
     * @param from -
     * @param to
     * @param subject
     * @param msg -
     */
    void sendMail(String from, String to, String subject, String msg);

    void sendMail(SimpleMailMessage msg);

    void sendRegistrationMail(User user);

}
