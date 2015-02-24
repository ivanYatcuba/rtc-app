package net.github.rtc.app.service.mail;


import net.github.rtc.app.model.entity.user.User;
import org.springframework.mail.SimpleMailMessage;

/**
 * Service that is responsible for mail management
 */
public interface MailService {

    /**
     * Send mail from one user to another
     * @param from sender address
     * @param to receiver address
     * @param subject topic of the mail
     * @param msg text of the message
     */
    void sendMail(String from, String to, String subject, String msg);

    /**
     * Send mail with registration details to mentioned user
     * @param user user on whose email the message will be send
     */
    void sendRegistrationMail(User user);
}
