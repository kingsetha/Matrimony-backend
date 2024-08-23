package com.ani.matrimony.service;

import com.ani.matrimony.model.Email;
import com.ani.matrimony.model.User;
import com.ani.matrimony.repo.EmailDetailRepo;
import com.ani.matrimony.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailDetailService {

    @Autowired
    private EmailDetailRepo emailDetailRepo;

    @Autowired
    private UserRepo userRepo;

    public void storeEmailDetails(int senderId, int receiverId, String subject, String body) {
        try {
            User sender = userRepo.findById(senderId)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));
            User receiver = userRepo.findById(receiverId)
                    .orElseThrow(() -> new RuntimeException("Receiver not found"));

            Email email = new Email();
            email.setSender(sender);
            email.setReceiver(receiver);
            email.setSubject(subject);
            email.setBody(body);
            email.setSentAt(new Date());

            emailDetailRepo.save(email);

            System.out.println("Email details saved successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            throw new RuntimeException("Error saving email details: " + e.getMessage());
        }
    }
}
