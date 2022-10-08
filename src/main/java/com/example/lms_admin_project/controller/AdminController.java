package com.example.lms_admin_project.controller;

import com.example.lms_admin_project.dto.*;
import com.example.lms_admin_project.model.Admin;
import com.example.lms_admin_project.service.IAdminService;
import com.example.lms_admin_project.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;
    @Autowired
    TokenUtility tokenUtility;

    //Adding Admin Details
    //localhost:3000/admin/add
    @PostMapping("/add")
    public ResponseEntity<String> AddUserDetails(@Valid @RequestBody AdminDTO adminDTO) {
        String response = adminService.insertAdminData(adminDTO);
        ResponseDTO respDTO = new ResponseDTO("Data Added Successfully and email sent to the Admin", response);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }
    //Get User Data by token
    @GetMapping("/getAdmin/{token}")
    public ResponseEntity<String> getUserDetails(@PathVariable String token) {
        Admin adminData = adminService.getAdminDataByToken(token);
        Long adminId = tokenUtility.decodeToken(token);
        ResponseDTO respDTO = new ResponseDTO("Data retrieved successfully for the ID: " + adminId, adminData);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }
    //Login check
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        String response = adminService.loginUser(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO("Login Status:", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Change password
    @PostMapping("/changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        String response = adminService.changePassword(changePasswordDTO);
        ResponseDTO responseDTO = new ResponseDTO("Password Status:", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Forgot Password
    @PostMapping("/forgotPassword/{email}")
    public ResponseEntity<ResponseDTO> forgotPassword(@PathVariable String email) {
        String response = adminService.forgotPassword(email);
        ResponseDTO responseDTO = new ResponseDTO("Password Link Shared to email", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //reset password to the new password(forgot old password)
    @PostMapping("/resetPassword")
    public ResponseEntity<ResponseDTO> resetPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        String response = adminService.resetPassword(forgotPasswordDTO);
        ResponseDTO responseDTO = new ResponseDTO("Password Reset", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Get all Admin Details
    @GetMapping("/allAdminList")
    public ResponseEntity<ResponseDTO> getAllUserDetails() {
        List<Admin> userDetailsList = adminService.getAllAdminList();
        ResponseDTO responseDTO = new ResponseDTO("All Admin Details, total count: " + userDetailsList.size(), userDetailsList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Get Admin Data by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDTO> getUserDetailsById(@PathVariable Long id) {
        Admin userDetails = adminService.getAdminDataById(id);
        ResponseDTO responseDTO = new ResponseDTO("User Details with the ID: " + id, userDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Get User Data by Email Address
    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String email) {
        Admin userDetails = adminService.getAdminDataByEmailAddress(email);
        ResponseDTO responseDTO = new ResponseDTO("User Details with the Email Address: " + email, userDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Updating the User Data using email Address
    @PutMapping("/update/{email}")
    public ResponseEntity<ResponseDTO> updateUserByEmailAddress(@PathVariable String email, @Valid @RequestBody AdminDTO adminDTO) {
        Admin userData = adminService.updateDataByEmail(adminDTO, email);
        ResponseDTO respDTO = new ResponseDTO("Data Update info", userData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    //Delete the User details by User ID
    @DeleteMapping("/delete/{id}/{email}")
    public ResponseEntity<ResponseDTO> deleteUserDataByID(@PathVariable Long id, @PathVariable String email) {
        Optional<Admin> deletedData = adminService.deleteData(id, email);
        ResponseDTO respDTO = new ResponseDTO("Deleted Successfully with user ID: "+id+", and e-mail sent, Below Data is deleted", deletedData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
