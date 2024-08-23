package com.ani.matrimony.service;

import java.util.Optional;

import com.ani.matrimony.model.Admin;

public interface AdminService {
	Optional<Admin> authenticate(String email, String password);

}
