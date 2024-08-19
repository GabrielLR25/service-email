package com.ms.email.dtos;

import com.ms.email.enums.StatusEmail;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseEmailDto {

    private UUID emailId;

    private StatusEmail statusEmail;

    private String emailTo;
}

