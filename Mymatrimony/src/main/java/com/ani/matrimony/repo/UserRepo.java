package com.ani.matrimony.repo;

import java.util.*;

import com.ani.matrimony.model.User;



public interface UserRepo {

	public String save(User user);

	public Optional<User> findByEmailAndPassword(String email, String password);
//	public  List<User> findByfirstnameContainingAndAgeAndReligion(String firstname, Integer age, String religion);
	public void deleteById(int id);
	public Optional<User> findById(int id);
	public List<User> findAll();
	public List<User> findByfirstnameContainingAndAgeAndReligion(Integer userid,String firstname, Integer age, String gender, String religion, String maritalstatus, String occupation);
	 public List<User> findByBlocked(Boolean blocked);
}
