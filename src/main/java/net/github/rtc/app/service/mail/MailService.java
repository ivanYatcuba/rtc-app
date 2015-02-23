package net.github.rtc.app.service.mail;


import net.github.rtc.app.model.entity.user.User;
import org.springframework.mail.SimpleMailMessage;

/**
 * Service that is responsible for mail management
 */
public interface MailService {

    /**
     *
     * @param from
     * @param to
     * @param subject
     * @param msg
     */
    void sendMail(String from, String to, String subject, String msg);

    /**
     *
     * @param msg
     */
    void sendMail(SimpleMailMessage msg);

    /**
     * Send mail with registration details to mentioned user
     * @param user
     */
    void sendRegistrationMail(User user);
}
