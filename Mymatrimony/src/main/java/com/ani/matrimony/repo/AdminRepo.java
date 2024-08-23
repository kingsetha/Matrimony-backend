package com.ani.matrimony.repo;

import java.util.Optional;

import com.ani.matrimony.model.Admin;
import com.ani.matrimony.model.User;

public interface AdminRepo {
	public Optional<Admin> findByEmailAndPassword(String email, String password);
	public String save(Admin user);

}
