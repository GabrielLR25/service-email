package com.ms.email.repositories;

import com.fasterxml.jackson.annotation.JsonValue;
import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
    //@Override
    @Query(value = "select owner_ref,email_to,email_from,status_email,send_date_email from tb_email where status_email =:statusEmail", nativeQuery = true)
    Optional<EmailModel> findByStatus(StatusEmail statusEmail);
}
