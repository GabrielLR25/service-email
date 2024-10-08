package com.ms.email.services;

import com.ms.email.dtos.ResponseEmailDto;
import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {
	
	@Autowired
	EmailRepository emailRepository;

	@Autowired
	private JavaMailSender emailSender;


	public EmailModel sendEmail(EmailModel emailModel) throws MailException {

		emailModel.setSendDateEmail(LocalDateTime.now());
		try{
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message);

			emailModel.setStatusEmail(StatusEmail.SENT);

		} catch (MailException e) {
			e.printStackTrace();
			emailModel.setStatusEmail(StatusEmail.ERROR);

		} finally {
			return emailRepository.save(emailModel);
		}
	}

	public Page<EmailModel> findAll(Pageable pageable){
		return emailRepository.findAll(pageable);
	}

	public Optional<EmailModel> findByStatus(StatusEmail statusEmail){
		return emailRepository.findByStatus(statusEmail);
	}

	public Optional<EmailModel> findById(UUID id){
		return emailRepository.findById(id);
	}

//	public ResponseEmailDto convertEmailModelToResponse(EmailModel model){
//		EmailModel emailModel = new EmailModel();
//		ResponseEmailDto responseEmailDto = new ResponseEmailDto();
//		BeanUtils.copyProperties(emailModel, responseEmailDto);
//		return responseEmailDto;
//	}

}
