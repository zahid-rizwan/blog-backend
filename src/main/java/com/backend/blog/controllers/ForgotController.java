package com.backend.blog.controllers;

import com.backend.blog.entities.ForgotPassword;
import com.backend.blog.entities.User;
import com.backend.blog.payloads.MailBody;
import com.backend.blog.payloads.UserDto;
import com.backend.blog.repositories.ForgotPasswordRepo;
import com.backend.blog.services.UserService;
import com.backend.blog.services.impl.EmailServices;
import com.backend.blog.services.utils.ChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotController {

  private static final Logger logger = Logger.getLogger(ForgotController.class.getName());

    @Autowired
    private UserService userService;
    @Autowired
    private EmailServices emailServices;
    @Autowired
    private ForgotPasswordRepo forgotPasswordRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        UserDto userDto = userService.getUserByEmail(email);
        int otp = otpGenerator();

        // Log the OTP for debugging
//        logger.info("Generated OTP: " + otp);

        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("This is the OTP for your forgot password: " + otp)
                .subject("OTP for Forgot Password")
                .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 700 * 1000))
                .user(userService.dtoToUser(userDto))
                .build();

        emailServices.SendSimpleMessage(mailBody);
        forgotPasswordRepo.save(forgotPassword);

        return ResponseEntity.ok("Email sent for verification");
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> otp(@PathVariable String email, @PathVariable Integer otp) {
        UserDto userDto = userService.getUserByEmail(email);
        System.out.println(userDto.getName());

       ForgotPassword forgotPassword = forgotPasswordRepo.findByOtpAndUser(otp,userService.dtoToUser(userDto))
               .orElseThrow(()->new UsernameNotFoundException("please provide valid email address"));

       if(forgotPassword.getExpirationTime().before(Date.from(Instant.now()))){
           forgotPasswordRepo.deleteById(forgotPassword.getFpid());
           return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
       }
      return ResponseEntity.ok("otp is verified");
    }
    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(
            @RequestBody ChangePassword changePassword,
            @PathVariable String email) {
        UserDto userDto = userService.getUserByEmail(email);
        ForgotPassword forgotPassword = forgotPasswordRepo.findByUser(userService.dtoToUser(userDto))
                .orElseThrow(()->new UsernameNotFoundException("please provide valid email address"));
        if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
            return new ResponseEntity<>("please enter the password again!", HttpStatus.EXPECTATION_FAILED);
        }
        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userDto.setPassword(encodedPassword);
        userService.updateUser(userDto, userDto.getId());
        forgotPasswordRepo.deleteById(forgotPassword.getFpid());

        return ResponseEntity.ok("password has successfully changed");
    }
    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
