package com.example.lms_admin_project.service;

import com.example.lms_admin_project.dto.AdminDTO;
import com.example.lms_admin_project.dto.ChangePasswordDTO;
import com.example.lms_admin_project.dto.ForgotPasswordDTO;
import com.example.lms_admin_project.dto.LoginDTO;
import com.example.lms_admin_project.exception.AdminException;
import com.example.lms_admin_project.model.Admin;
import com.example.lms_admin_project.repository.AdminRepo;
import com.example.lms_admin_project.utility.EmailSenderService;
import com.example.lms_admin_project.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService{
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    EmailSenderService emailSender;

    //Adding Admin details
    @Override
    public String insertAdminData(AdminDTO adminDTO) {
        LocalDateTime creatorStamp = LocalDateTime.now();
        LocalDateTime updatedStamp;
        Admin adminDetails = new Admin(adminDTO.getFirstName(), adminDTO.getLastName(), adminDTO.getMobileNumber(), adminDTO.getEmailId(), adminDTO.getPassword(), adminDTO.getProfilePic(), adminDTO.getStatus(), creatorStamp, null);
        adminRepo.save(adminDetails);
        String token = tokenUtility.createToken(adminDetails.getId());
//        sending email
        emailSender.sendEmail(adminDetails.getEmailId(), "Data Added!!!", "Your Account is registered! Please Click on the below link for the details."+"\n"+"http://localhost:3000/admin/getAdmin/"+token);
        return token;
    }
    //Get Admin Details by Token Id
    @Override
    public Admin getAdminDataByToken(String token) {
        Long adminId = tokenUtility.decodeToken(token);
        Optional<Admin> existingData = adminRepo.findById(adminId);
        if(existingData.isPresent()){
            return existingData.get();
        }else
            throw new AdminException("Invalid Token");
    }
    //Login check
    @Override
    public String loginUser(LoginDTO loginDTO) {
        Optional<Admin> adminDetails = Optional.ofNullable(adminRepo.findByEmailAddress(loginDTO.emailId));
        if(adminDetails.isPresent()) {
            if(adminDetails.get().getPassword().equals(loginDTO.getPassword())) {
                emailSender.sendEmail(adminDetails.get().getEmailId(), "Login", "Login Successful!");
                return "Login Successful!!";
            } else
                emailSender.sendEmail(adminDetails.get().getEmailId(), "Login", "You have entered Invalid password!");
            throw new AdminException("Login Failed, Wrong Password!!!");
        }else
            throw new AdminException("Login Failed, Entered wrong email or password!!!");
    }
    //Change Password
    @Override
    public String changePassword(ChangePasswordDTO changePasswordDTO) {
        Optional<Admin> adminDetails = Optional.ofNullable(adminRepo.findByEmailAddress(changePasswordDTO.getEmailId()));
        String password = changePasswordDTO.getPassword();
        if(adminDetails.isPresent() && adminDetails.get().getPassword().equals(changePasswordDTO.getOldPassword())){
            adminDetails.get().setPassword(password); //changing password
            //Sending Email
            emailSender.sendEmail(adminDetails.get().getEmailId(), "Password Change!", "Password Changed Successfully!");
            adminRepo.save(adminDetails.get());
            return "Password Changed and email sent!";
        }else
            return "Invalid Email Address or Old Password";
    }
    //Sending email for the forgot password, will receive email of reset password
    @Override
    public String forgotPassword(String email) {
        Admin adminDetails = adminRepo.findByEmailAddress(email);
        if(adminDetails != null){
            emailSender.sendEmail(adminDetails.getEmailId(), "Forgot Password", "Please click on the below link to change password"+"\nhttp://localhost:3000/admin/resetPassword");
            return "Password link shared to your email address";
        }else
            throw new AdminException("Invalid Email Address");
    }
    //reset password
    @Override
    public String resetPassword(ForgotPasswordDTO forgotPasswordDTO) {
        Admin userDetails = adminRepo.findByEmailAddress(forgotPasswordDTO.getEmailId());
        if(userDetails != null){
            userDetails.setPassword(forgotPasswordDTO.getNewPassword());
            adminRepo.save(userDetails);
            return "Password Reset successful!";
        }else
            throw new AdminException("Invalid Email Address");
    }
    //Get all Admin lists
    @Override
    public List<Admin> getAllAdminList() {
        List<Admin> userDetailsList = adminRepo.findAll();
        if (userDetailsList.isEmpty()) {
            throw new AdminException("No Admin Registered yet!!!!");
        } else
            return userDetailsList;
    }
    //Get Admin Details by Id
    @Override
    public Admin getAdminDataById(Long id) {
        Admin userDetails = adminRepo.findById(id).orElse(null);
        if (userDetails != null) {
            return userDetails;
        } else
            throw new AdminException("Admin ID: " + id + ", does not exist");
    }
    //Get data by email Address
    @Override
    public Admin getAdminDataByEmailAddress(String email) {
        Admin userDetails = adminRepo.findByEmailAddress(email);
        if (userDetails != null) {
            return userDetails;
        } else
            throw new AdminException("Email Address: " + email + ", does not exist");
    }
    //update data by email address
    @Override
    public Admin updateDataByEmail(AdminDTO adminDTO, String email) {
        Admin adminDetails = adminRepo.findByEmailAddress(email);
        if (adminDetails != null && adminDTO.getEmailId().equals(email)) {
            LocalDateTime updatedStamp = LocalDateTime.now();

            adminDetails.setFirstName(adminDTO.getFirstName());
            adminDetails.setLastName(adminDTO.getLastName());
            adminDetails.setMobileNumber(adminDTO.getMobileNumber());
            adminDetails.setEmailId(adminDTO.getEmailId());
            adminDetails.setEmailId(adminDTO.getEmailId());
            adminDetails.setPassword(adminDTO.getPassword());
            adminDetails.setStatus(adminDTO.getStatus());
            adminDetails.setUpdatedStamp(updatedStamp);
            String token = tokenUtility.createToken(adminDetails.getId());
            //sending email
            emailSender.sendEmail(adminDetails.getEmailId(), "Data Updated!!!", "Please Click on the below link for the updated details."+"\n"+"http://localhost:3000/admin/getAdmin/"+token);

            return adminRepo.save(adminDetails);
        } else
            throw new AdminException("Invalid Email Address: " + email);
    }
    //delete data by user id
    @Override
    public Optional<Admin> deleteData(Long id, String email) {
        Optional<Admin> adminDetails = adminRepo.findById(id);
        if(adminDetails.isPresent() && adminDetails.get().getEmailId().equals(email)){
                adminRepo.deleteById(id);
                //sending email
                emailSender.sendEmail(adminDetails.get().getEmailId(), "Data Deleted!!!", "Your Data deleted successfully from the LMS-Admin Project, with the user ID: "+id);
        }else
            throw new AdminException("Invalid Admin Id || email");
        return adminDetails;
    }
}
