package com.example.lms_admin_project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AdminDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message="Invalid First Name(First Letter Should be in Upper Case and min 3 Characters.)")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message="Invalid Last Name(First Letter Should be in Upper Case and min 3 Characters.")
    private String lastName;
    @Pattern(regexp = "^[1-9]{2}[0-9]{10}$", message="Invalid Contact Number(Should have Country Code and must be 10 digit number) example: 919234567890")
    private String mobileNumber;
    @NotEmpty(message="Email Address Cannot be Empty")
    private String emailId;
    @NotEmpty(message="Profile Pic Cannot be Empty")
    private String profilePic;
    /*
     * (?=.*[A-Z]) represents an upper case character that must occur at least once.
     * (?=.*[0-9]) represents a digit must occur at least once.
     * (?+.*[@#$%^&*()] represent the special symbol at least once.
     * (?=.*[a-zA-z0-9]) represents a lower case character or number must occur at least once.
     * {8,} represents at least 8 or more characters.
     */
    @NotEmpty(message="Password Cannot be Empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&*()-+=])([a-zA-Z0-9@._-]).{8,}$", message="Invalid Password\n(1. Upper case character that must occur at least once.\n" +
            "2. A digit must occur at least once.\n3. Special symbol at least once.\n4. lower case character or number must occur at least once.\n5. Represents at least 8 or more characters.)")
    private String password;
    @NotEmpty(message="Status Cannot be Empty")
    private String status;
}
