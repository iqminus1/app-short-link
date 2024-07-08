package uz.pdp.appshortlink.service;

public interface MailService {
    void sendMessage(String email,String subject,String text);
}
