package com.ms.email.configs;


import com.ms.email.repositories.EmailRepository;
import com.ms.email.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AutomaticSendMailConfig {

    @Autowired
    EmailService emailService;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

//    @Scheduled(cron = "0 25 12 * * *", zone = "America/Sao_Paulo")
//    public EmailModel scheduleSendMail() {
//        LocalDateTime dateSend = LocalDateTime.now();
//        if (dateSend.isBefore(LocalDateTime.now())){
//            EmailModel emailModel = new EmailModel();
//            try{
//                SimpleMailMessage message = new SimpleMailMessage();
//                message.setFrom(emailModel.getEmailFrom());
//                message.setTo(emailModel.getEmailTo());
//                message.setSubject(emailModel.getSubject());
//                message.setText(emailModel.getText());
//                emailSender.send(message);
//
//                emailModel.setStatusEmail(StatusEmail.SENT);
//
//            } catch (MailException e) {
//                emailModel.setStatusEmail(StatusEmail.ERROR);
//
//            } finally {
//
//                return emailRepository.save(emailModel);
//            }
//        }
//        return null;
//    }

    @Bean
    @Scheduled(cron = "0 30 12 * * *", zone = "America/Sao_Paulo")
    public void count(){
        long qnte = emailRepository.count();
        log.info("Atualmente existem " + qnte + " emails enviados.");
    }
}

