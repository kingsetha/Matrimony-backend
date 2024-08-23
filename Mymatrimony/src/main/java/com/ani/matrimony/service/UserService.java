package com.ani.matrimony.service;

import java.util.Optional;

import com.ani.matrimony.model.User;
import java.util.*;

public interface UserService {

	public String addUser(User user);

	Optional<User> authenticate(String email, String password);
	List<User> getUsers(Map<String, String> params);

    public void deleteUser(int id);

     public void updateUser(int id, User user);
     public Optional<User> getUserById(int id);
     public List<User> getAllUsers() ;
     public List<User> findBlockedUsers(Boolean blocked);
     
     public String blockUser(int userId);
     public String unblockUser(int userId);
}
