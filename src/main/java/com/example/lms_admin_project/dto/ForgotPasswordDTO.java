package com.example.lms_admin_project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForgotPasswordDTO {
    private String emailId;
    private String newPassword;
}
