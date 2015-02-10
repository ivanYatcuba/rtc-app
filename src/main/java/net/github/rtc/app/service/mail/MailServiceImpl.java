package net.github.rtc.app.service.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.github.rtc.app.model.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by denis on 21.11.14.
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
    private static final String SENDER_MAIL = "rtcsender@gmail.com";
    private static final String REGISTRATION_SUBJECT = "Registration confirmation";

    @Autowired
    @Qualifier("rtcMailSender")
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendMail(String from, String to, String subject, String msg) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
    @Async
    public void sendMail(SimpleMailMessage msg) {
        mailSender.send(msg);
    }
    @Async
    public void sendRegistrationMail(User user) {
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(MailServiceImpl.class, "/");
        try {
            Template emailTemplate;
            emailTemplate = config.getTemplate("templates/registrationMail.ftl");
            final StringWriter writer = new StringWriter();
            final Map<String, Object> templateMap = new HashMap<String, Object>();
            templateMap.put("userName", user.getName());
            templateMap.put("link", "http://146.185.176.193/rtc-app/user/userCourses");
            emailTemplate.process(templateMap, writer);
            sendMail(SENDER_MAIL, user.getEmail(), REGISTRATION_SUBJECT, writer.toString());
        } catch (IOException e) {
             e.printStackTrace();
             sendMail(SENDER_MAIL, user.getEmail(), REGISTRATION_SUBJECT, "Welcome," + user.getName() + "! ");
        } catch (TemplateException e) {
             e.printStackTrace();
             sendMail(SENDER_MAIL, user.getEmail(), REGISTRATION_SUBJECT, "Welcome!");
         }

    }

}
