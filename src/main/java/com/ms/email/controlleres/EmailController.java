package com.ms.email.controlleres;

import com.ms.email.dtos.EmailDto;
import com.ms.email.enums.StatusEmail;
import jakarta.validation.Valid;
import com.ms.email.models.EmailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ms.email.services.EmailService;

import java.util.UUID;

@RestController
public class EmailController {
//ms-email no google, senha no properties
    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);

    }

    @GetMapping("/status-email")
    public ResponseEntity<EmailModel> getEmailByStatus(@RequestBody @Valid StatusEmail statusEmail){
        emailService.findByStatus(statusEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
