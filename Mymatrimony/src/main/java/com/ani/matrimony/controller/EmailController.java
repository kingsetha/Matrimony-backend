package com.ani.matrimony.controller;

import com.ani.matrimony.service.Email1Service;
import com.ani.matrimony.service.EmailDetailService;

import jakarta.mail.MessagingException;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class EmailController {

    @Autowired
    private Email1Service emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(
            @RequestParam String subject,
            @RequestParam String body,
            @RequestParam(required = false) MultipartFile attachment
    ) {
        try {
            emailService.sendEmailToAllUsers(subject, body, attachment);
            return ResponseEntity.ok("Email sent successfully to all users");
        } catch (MessagingException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
        }
    }
}