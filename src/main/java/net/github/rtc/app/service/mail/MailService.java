package net.github.rtc.app.service.mail;


import org.springframework.mail.SimpleMailMessage;

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


}
