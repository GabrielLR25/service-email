package com.ms.email.services;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import jakarta.mail.MessagingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private EmailRepository emailRepository;

    @Test
    public void sendEmail() {
        EmailModel emailModel = createEmailModel();
        emailService.sendEmail(emailModel);
    }

    @Test
    public void sendSimpleMailMessage(){
        SimpleMailMessage message = createSender();
        emailSender.send(message);
    }

    @Test
    public void whenSendSimpleMailMessageReturnError() {
        SimpleMailMessage messageError = createSender();
        EmailModel emailModel = createEmailModel();
        messageError.setText("Error ao enviar email");
        emailModel.setStatusEmail(StatusEmail.ERROR);
        assertThrows(MessagingException.class, () -> emailSender.send(messageError));

//        MailException mailException =
//               assertThrows(MailException.class, ()-> emailSender.send(messageError));

       //assertThat(mailException.getMessage()).isEqualTo("Error ao enviar email");
    }

    @Test
    public void findAll() {

    }

    @Test
    public void findByStatus() {
        StatusEmail statusEmail = StatusEmail.SENT;
        emailService.findByStatus(statusEmail);
    }

    private EmailModel createEmailModel(){
        EmailModel emailModel = EmailModel.builder()
                .emailId(UUID.randomUUID())
                .ownerRef("")
                .emailFrom("")
                .emailTo("")
                .subject("")
                .text("")
                .sendDateEmail(LocalDateTime.now())
                .statusEmail(StatusEmail.SENT)
                .build();

        return emailModel;
    }

    private SimpleMailMessage createSender(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo("");
        message.setSubject("");
        message.setText("");

        return message;
    }
}