package com.example.lms_admin_project.dto;

import com.example.lms_admin_project.model.Admin;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDTO {
    String message;
    Object response;
    public ResponseDTO(String message, String response) {
        this.message = message;
        this.response = response;
    }

    public ResponseDTO(String message, Admin response) {
        this.message = message;
        this.response = response;
    }

    public ResponseDTO(String message, List<Admin> response) {
        this.message = message;
        this.response = response;
    }

    public ResponseDTO(String message, Optional<Admin> response) {
        this.message = message;
        this.response = response;
    }
}
