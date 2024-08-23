//package com.ani.matrimony.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Column;
//
//@Entity
//public class PhoneNumberRequest {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private Long userId;
//
//    @Column(nullable = false)
//    private String requestType;
//
////    @Column(nullable = false)
//    private String status; // 'pending', 'approved', 'denied'
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getRequestType() {
//        return requestType;
//    }
//
//    public void setRequestType(String requestType) {
//        this.requestType = requestType;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//	public PhoneNumberRequest() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public PhoneNumberRequest(Long id, Long userId, String requestType, String status) {
//		super();
//		this.id = id;
//		this.userId = userId;
//		this.requestType = requestType;
//		this.status = status;
//	}
//}
