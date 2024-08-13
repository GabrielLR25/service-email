package com.ms.email.controllers;

import com.ms.email.dtos.EmailDto;
import com.ms.email.enums.StatusEmail;
import com.ms.email.services.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmailControllerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailController emailController;

    @Before
    public void before(){}

    @Test
    public void sendingEmail() {
        EmailDto emailDto = createEmailDTO();
        emailController.sendingEmail(emailDto);
    }

    @Test
    public void getEmailByStatus() {
        StatusEmail statusEmail = StatusEmail.SENT;
        emailController.getEmailByStatus(statusEmail);
    }

    private EmailDto createEmailDTO(){
        EmailDto emailDto = EmailDto.builder()
                .ownerRef("Teste")
                .emailFrom("Teste")
                .emailTo("Teste")
                .subject("Teste")
                .text("Teste")
                .build();
        return emailDto;
    }
}