package com.backend.blog.repositories;

import com.backend.blog.entities.ForgotPassword;
import com.backend.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepo extends JpaRepository<ForgotPassword, Integer> {
    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user =?2")
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
    Optional<ForgotPassword> findByUser(User user);

}
