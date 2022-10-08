package com.example.lms_admin_project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordDTO {
    private String emailId;
    private String oldPassword;
    private String password;
}
