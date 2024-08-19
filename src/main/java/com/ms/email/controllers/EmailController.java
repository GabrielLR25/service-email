package com.ms.email.controllers;

import com.ms.email.dtos.RequestEmailDto;
import com.ms.email.dtos.ResponseEmailDto;
import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class EmailController {
//ms-email no google, senha no properties
    @Autowired
    EmailService emailService;

    Logger logger = LogManager.getLogger(EmailController.class);

    @PostMapping("/sending-email")
    public ResponseEntity<ResponseEmailDto> sendingEmail(@RequestBody @Valid RequestEmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        ResponseEmailDto responseEmailDto = new ResponseEmailDto();
        emailService.sendEmail(emailModel);
        BeanUtils.copyProperties(emailModel, responseEmailDto);
        return new ResponseEntity<>(responseEmailDto, HttpStatus.CREATED);

    }

    @GetMapping("/status-email")
    public ResponseEntity<EmailModel> getEmailByStatus(@RequestBody @Valid StatusEmail statusEmail){
        emailService.findByStatus(statusEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all-emails")
    public ResponseEntity<Page<EmailModel>> getEmails(@PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable){
        return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<Object> getEmailById(@PathVariable(value = "id")UUID id){
        Optional<EmailModel> emailModelOptional = emailService.findById(id);
        return emailModelOptional.<ResponseEntity<Object>>
                map(emailModel -> ResponseEntity.status(HttpStatus.OK).body(emailModel))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found"));

    }
}
