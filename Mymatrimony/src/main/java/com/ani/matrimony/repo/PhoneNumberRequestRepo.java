//package com.ani.matrimony.repo;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import java.util.*;
//import com.ani.matrimony.model.PhoneNumberRequest;
//
//@Repository
//public interface PhoneNumberRequestRepo extends JpaRepository<PhoneNumberRequest, Long> {
//    PhoneNumberRequest findByUserIdAndRequestType(Long userId, String requestType);
//    @Query("SELECT r FROM PhoneNumberRequest r WHERE r.userid = :userid")
//    List<PhoneNumberRequest> findByUserId(@Param("userid") Long userId);
//}
