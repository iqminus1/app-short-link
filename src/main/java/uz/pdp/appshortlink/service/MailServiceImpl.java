package uz.pdp.appshortlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)
public class MailServiceImpl implements MailService {
    @Autowired
    private MailSender mailSender;

    @Override
    @Async
    public void sendMessage(String email, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setFrom("Short-link@gmail.com");
        mailSender.send(simpleMailMessage);
    }
}
