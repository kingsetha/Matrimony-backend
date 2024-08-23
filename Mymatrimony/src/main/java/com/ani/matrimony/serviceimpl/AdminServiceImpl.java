package com.ani.matrimony.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ani.matrimony.model.Admin;
import com.ani.matrimony.repo.AdminRepo;
import com.ani.matrimony.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{
	
	 private AdminRepo adminRepository;

	    @Autowired
	    public AdminServiceImpl(AdminRepo userRepository) {
	        this.adminRepository = userRepository;
	    }
	 @Override
	    public Optional<Admin> authenticate(String email, String password) {
	        return adminRepository.findByEmailAndPassword(email, password);
	    }

}
