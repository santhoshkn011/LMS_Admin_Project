package com.example.lms_admin_project.repository;

import com.example.lms_admin_project.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    @Query(value = "SELECT * FROM admin WHERE email_id=:email", nativeQuery = true)
    Admin findByEmailAddress(String email);
}
