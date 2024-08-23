package com.ani.matrimony.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ani.matrimony.model.Email;
import com.ani.matrimony.model.User;
import com.ani.matrimony.repo.EmailRepo;
import com.ani.matrimony.repo.UserRepo;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class Email1Service {

    @Autowired
    private JavaMailSender javaMailSender;

    private final String fromEmail = "noreply@yourdomain.com"; // Replace with your "noreply" email address

    public void sendEmail(String to, String subject, String text, MultipartFile attachment) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true); // Set true to enable HTML content

        if (attachment != null && !attachment.isEmpty()) {
            helper.addAttachment(attachment.getOriginalFilename(), new ByteArrayResource(attachment.getBytes()));
        }

        javaMailSender.send(mimeMessage);
        System.out.println("Mail sent");
    }
  

        @Autowired
        private EmailRepo emailRepository;

        @Autowired
        private UserRepo userRepository; 

        public void sendEmailToAllUsers(String subject, String body, MultipartFile attachment) throws MessagingException, IOException {
            List<User> allUsers = userRepository.findAll();

            for (User user : allUsers) {
                sendEmail(user.getEmail(), subject, body, attachment);
                
                // Save to database
                Email email = new Email();
                email.setSender(user);  // Assuming the current user is the sender
                email.setReceiver(user);
                email.setSubject(subject);
                email.setBody(body);
                email.setSentAt(new Date());

                emailRepository.save(email);
            }
        }

}
