package com.ms.email.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEmailDto {
	
	@NotBlank
    private String ownerRef;
	
    @NotBlank
    @Email
    private String emailFrom;
    
    @NotBlank
    @Email
    private String emailTo;
    
    @NotBlank
    private String subject;
    
    @NotBlank
    private String text;

}
