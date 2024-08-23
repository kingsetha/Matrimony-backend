package com.ani.matrimony.serviceimpl;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.ani.matrimony.model.User;
import com.ani.matrimony.repo.UserRepo;
import com.ani.matrimony.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepository;

    @Autowired
    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String addUser(User user) {
        userRepository.save(user);
        return "User added successfully";
    }

    @Override
//    public Optional<User> authenticate(String email, String password) {
//        return userRepository.findByEmailAndPassword(email, password);
//    }
    public Optional<User> authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.isBlocked()) {
                // If the user is blocked, return an empty Optional
                return Optional.empty();
            }
            return Optional.of(user);
        }
        return Optional.empty();
    }
    public List<User> getUsers(Map<String, String> params) {
        // Extract parameters
        String useridStr = params.get("userid");
        String firstname = params.get("firstname");

        Integer userid = null;
        if (useridStr != null && !useridStr.trim().isEmpty()) {
            try {
                userid = Integer.parseInt(useridStr);
            } catch (NumberFormatException e) {
                // Log error or handle invalid userid value
                System.err.println("Invalid userid value: " + useridStr);
            }
        }

        Integer age = null;
        String ageStr = params.get("age");
        if (ageStr != null && !ageStr.trim().isEmpty()) {
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                // Log error or handle invalid age value
                System.err.println("Invalid age value: " + ageStr);
            }
        }

        String gender = params.get("gender");
        String religion = params.get("religion");
        String maritalstatus = params.get("maritalstatus");
        String occupation = params.get("occupation");

        // Use the converted userid and other parameters to query the repository
        return userRepository.findByfirstnameContainingAndAgeAndReligion(
                userid, firstname, age, gender, religion, maritalstatus, occupation);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

//   @Override
//
//    public void updateUser(int id, User user) {
//        Optional<User> existingUser = userRepository.findById(id);
//        if (existingUser.isPresent()) {
//            User updatedUser = existingUser.get();
//            updatedUser.setFirstname(user.getFirstname());
//            updatedUser.setAge(user.getAge());
//            updatedUser.setHeight(user.getHeight());
//            updatedUser.setReligion(user.getReligion());
//            updatedUser.setCaste(user.getCaste());
//            updatedUser.setGender(user.getGender());
//            updatedUser.setOccupation(user.getOccupation());
//            updatedUser.setCurrentsalary(user.getCurrentsalary());
//            updatedUser.setDOB(user.getDOB());
//            updatedUser.setMobilenumber(user.getMobilenumber());
//            updatedUser.setMaritalstatus(user.getMaritalstatus());
//            // Update other fields as necessary
//            userRepository.save(updatedUser);
//        }
//    }
    @Override
    public void updateUser(int id, User user) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update fields
            existingUser.setFirstname(user.getFirstname());
            existingUser.setLastname(user.getLastname());
            existingUser.setEmail(user.getEmail());
            existingUser.setMobilenumber(user.getMobilenumber());
            existingUser.setGender(user.getGender());
            existingUser.setDOB(user.getDOB());
            existingUser.setMaritalstatus(user.getMaritalstatus());
            existingUser.setReligion(user.getReligion());
            existingUser.setCurrentsalary(user.getCurrentsalary());
            existingUser.setHeight(user.getHeight());
            existingUser.setWeight(user.getWeight());
            existingUser.setOccupation(user.getOccupation());
            existingUser.setPassword(user.getPassword());
            existingUser.setAddress(user.getAddress());

            // Handle new fields
            existingUser.setZodiacsign(user.getZodiacsign());
            existingUser.setBirthstar(user.getBirthstar());
            existingUser.setGothra(user.getGothra());

            userRepository.save(existingUser);
        }
    }
    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }
    @Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}
    @Override
    public List<User> findBlockedUsers(Boolean blocked) {
        return userRepository.findByBlocked(blocked);
    }


    @Override
    public String blockUser(int userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setBlocked(true);
            userRepository.save(user);
            return "User blocked successfully";
        } else {
            return "User not found";
        }
    }

    @Override
    public String unblockUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBlocked(false);
            userRepository.save(user);
            return "User unblocked successfully";
        }
        return "User not found";
    }
   

}
