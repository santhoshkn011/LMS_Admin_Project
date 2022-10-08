package com.example.lms_admin_project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public @ToString class LoginDTO {
    public String emailId;
    public String password;
}