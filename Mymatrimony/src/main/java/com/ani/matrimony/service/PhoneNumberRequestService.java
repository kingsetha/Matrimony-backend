//package com.ani.matrimony.service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ani.matrimony.model.PhoneNumberRequest;
//import com.ani.matrimony.repo.PhoneNumberRequestRepo;
//import com.ani.matrimony.repo.UserRepo;
//
//@Service
//public class PhoneNumberRequestService {
//    @Autowired
//    private PhoneNumberRequestRepo requestRepository;
//    @Autowired
//    private UserRepo userRepository;
//
//    public PhoneNumberRequest createRequest(Long userId) {
//        PhoneNumberRequest request = new PhoneNumberRequest();
//        request.setUserId(userId);
//        request.setRequestType("viewPhoneNumber");
//        request.setStatus("pending");
//        return requestRepository.save(request);
//    }
//
//    public PhoneNumberRequest getRequest(Long userId, String requestType) {
//        return requestRepository.findByUserIdAndRequestType(userId, requestType);
//    }
//}
