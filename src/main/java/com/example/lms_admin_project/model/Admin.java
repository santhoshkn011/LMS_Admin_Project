package com.example.lms_admin_project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AdminId", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailId;
    private String password;
    private String profilePic;
    private String status;
    private LocalDateTime creatorStamp;
    private LocalDateTime updatedStamp;

//    public Admin(AdminDTO adminDTO){
//        this.firstName = adminDTO.getFirstName();
//        this.lastName = adminDTO.getLastName();
//        this.mobileNumber = adminDTO.getMobileNumber();
//        this.emailId = adminDTO.getEmailId();
//        this.password = adminDTO.getPassword();
//        this.profilePic = adminDTO.getProfilePic();
//        this.status = adminDTO.getStatus();
//    }
    public Admin(String firstName, String lastName, String mobileNumber, String emailId, String password, String profilePic, String status, LocalDateTime creatorStamp, LocalDateTime updatedStamp){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.password = password;
        this.profilePic = profilePic;
        this.status = status;
        this.creatorStamp = creatorStamp;
        this.updatedStamp = updatedStamp;
    }
}
