package com.ani.matrimony.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ani.matrimony.model.LoginResponse;
//import com.ani.matrimony.model.PhoneNumberRequest;
import com.ani.matrimony.model.User;
//import com.ani.matrimony.repo.PhoneNumberRequestRepo;
import com.ani.matrimony.service.EmailService;
import com.ani.matrimony.model.Admin;
import com.ani.matrimony.model.AdminResponse;
import com.ani.matrimony.serviceimpl.AdminServiceImpl;
import com.ani.matrimony.serviceimpl.UserServiceImpl;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/User")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserServiceImpl service;
    @Autowired
    AdminServiceImpl Aservice;

    static final String SUCCESS = "Success";
    static final String FAILURE = "Failure";

    @PostMapping("/register")
    public String addUser(@RequestBody User user) {
        try {
            service.addUser(user);
            return "Registered Successfully";
        } catch (Exception e) {
            return "Failure";
        }
    }
    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Optional<User> authenticatedUser = service.authenticate(user.getEmail(), user.getPassword());
            if (authenticatedUser.isPresent()) {
                User userDetails = authenticatedUser.get();
                String role = userDetails.getRole();
                int id = userDetails.getUserid();
                return ResponseEntity.ok(new LoginResponse(id, "LoginSuccess", role));
            } else {
                Optional<Admin> authenticatedAdmin = Aservice.authenticate(user.getEmail(), user.getPassword());
                if (authenticatedAdmin.isPresent()) {
                    Admin adminDetails = authenticatedAdmin.get();
                    String role = adminDetails.getRole();
                    int id = adminDetails.getId();
                    return ResponseEntity.ok(new AdminResponse(id, "LoginSuccess", role));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error. Please try again later.");
        }
    }


    @GetMapping("/all")
	public List<User> getUsers()
	{
		return service.getAllUsers();
	}

    @GetMapping
    public List<User> getUsers(@RequestParam Map<String, String> params) {
        return service.getUsers(params);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            service.deleteUser(id);
            return "User deleted successfully";
        } catch (Exception e) {
            return "Failure";
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(
        @PathVariable int id,
        @RequestBody User userUpdateRequest
    ) {
        try {
            // Fetch the existing user
            Optional<User> existingUserOpt = service.getUserById(id);

            if (existingUserOpt.isPresent()) {
                User existingUser = existingUserOpt.get();

                
                existingUser.setFirstname(userUpdateRequest.getFirstname());
                existingUser.setLastname(userUpdateRequest.getLastname());
                existingUser.setEmail(userUpdateRequest.getEmail());
                existingUser.setMobilenumber(userUpdateRequest.getMobilenumber());
                existingUser.setGender(userUpdateRequest.getGender());
                existingUser.setDOB(userUpdateRequest.getDOB());
                existingUser.setMaritalstatus(userUpdateRequest.getMaritalstatus());
                existingUser.setReligion(userUpdateRequest.getReligion());
                existingUser.setCurrentsalary(userUpdateRequest.getCurrentsalary());
                existingUser.setHeight(userUpdateRequest.getHeight());
                existingUser.setWeight(userUpdateRequest.getWeight());
                existingUser.setOccupation(userUpdateRequest.getOccupation());
                existingUser.setPassword(userUpdateRequest.getPassword());
                existingUser.setAddress(userUpdateRequest.getAddress());

                // Handle new fields
                existingUser.setZodiacsign(userUpdateRequest.getZodiacsign());
                existingUser.setBirthstar(userUpdateRequest.getBirthstar());
                existingUser.setGothra(userUpdateRequest.getGothra());

                // Save the updated user
                service.updateUser(id, existingUser);

                return ResponseEntity.ok("User updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
    }



    @GetMapping("/getProfileData/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body,
            @RequestParam(required = false) MultipartFile attachment
    ) {
        try {

            emailService.sendEmail(to, subject, body, attachment);
            return ResponseEntity.ok("Email sent successfully");
        } catch (MessagingException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
        }
    }

    @GetMapping("/blocked")
    public List<User> findBlockedUsers(@RequestParam boolean blocked) {
        return service.findBlockedUsers(blocked);
    }

    @PostMapping("/block/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable int userId) {
        String response = service.blockUser(userId);
        if (response.equals("User blocked successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping("/unblock/{userId}")
    public ResponseEntity<String> unblockUser(@PathVariable int userId) {
        String response = service.unblockUser(userId);
        if (response.equals("User unblocked successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

}
