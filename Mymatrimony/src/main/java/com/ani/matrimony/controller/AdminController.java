package com.ani.matrimony.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ani.matrimony.model.Admin;
import com.ani.matrimony.model.LoginResponse;
import com.ani.matrimony.model.Admin;
import com.ani.matrimony.serviceimpl.AdminServiceImpl;
@RestController
@RequestMapping("/Admin")
@CrossOrigin("*")
public class AdminController {
	 @Autowired
	    AdminServiceImpl service;

	    static final String SUCCESS = "Success";
	    static final String FAILURE = "Failure";

//	@PostMapping
//    public String login(@RequestBody Admin admin) {
//        Optional<Admin> admin1 = service.authenticate(admin.getEmail(), admin.getPassword());
//        if (admin1.isPresent()) {
//            return "Login successful";
//        } else {
//            return "Login failed: Invalid firstname or password";
//        }
//    }
	    @PostMapping
	    public ResponseEntity<?> loginAdmin(@RequestBody Admin admin) {
	        try {
	            Optional<Admin> authenticatedAdmin = service.authenticate(admin.getEmail(), admin.getPassword());
	            if (authenticatedAdmin.isPresent()) {
	                Admin AdminDetails = authenticatedAdmin.get();
	                String role = AdminDetails.getRole();
	                int id = AdminDetails.getId();
	                return ResponseEntity.ok(new LoginResponse(id, "LoginSuccess", role));
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials. Please try again.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error. Please try again later.");
	        }
	    }
	    
}
