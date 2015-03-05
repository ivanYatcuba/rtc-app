package net.github.rtc.app.service.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.springframework.mail.SimpleMailMessage;

@RunWith(BlockJUnit4ClassRunner.class)
public class MailServiceImplTest {

    @InjectMocks
    private MailService mailService = new MailServiceImpl();

    @Test(expected = NullPointerException.class)
    public void testSendMail() {
        mailService.sendMail("", "", "", "");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo("");
        message.setSubject("");
        message.setText("");
        mailService.sendMail(message);
    }
}
