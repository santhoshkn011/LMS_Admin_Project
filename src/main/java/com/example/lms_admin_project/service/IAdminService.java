package com.example.lms_admin_project.service;

import com.example.lms_admin_project.dto.AdminDTO;
import com.example.lms_admin_project.dto.ChangePasswordDTO;
import com.example.lms_admin_project.dto.ForgotPasswordDTO;
import com.example.lms_admin_project.dto.LoginDTO;
import com.example.lms_admin_project.model.Admin;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    String insertAdminData(AdminDTO adminDTO);

    Admin getAdminDataByToken(String token);

    String loginUser(LoginDTO loginDTO);

    String changePassword(ChangePasswordDTO changePasswordDTO);

    String forgotPassword(String email);

    String resetPassword(ForgotPasswordDTO forgotPasswordDTO);

    List<Admin> getAllAdminList();

    Admin getAdminDataById(Long id);

    Admin getAdminDataByEmailAddress(String email);

    Admin updateDataByEmail(AdminDTO adminDTO, String email);

//    Admin deleteData(Long id);

    //delete data by user id
    Optional<Admin> deleteData(Long id, String email);
}
