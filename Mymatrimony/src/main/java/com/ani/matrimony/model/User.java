package com.ani.matrimony.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "userdb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String confirmpassword;
    @Column
    private String gender;
    @Column
    private String mobilenumber;
    @Column
    private String address;
    @Column
    private int age;
    @Column
    private String religion;
    @Column
    private String caste;
    @Column
    private String maritalstatus;
    @Column
    private String currentsalary;
    @Column
    private String occupation;
    @Column
    private String height;
    @Column
    private String weight;
    @Column
    private String DOB;
    @Column
    private String role;
    @Column
    private String zodiacsign;
    @Column
    private String birthstar;
    @Column
    private String gothra;

    public String getZodiacsign() {
		return zodiacsign;
	}

	public void setZodiacsign(String zodiacsign) {
		this.zodiacsign = zodiacsign;
	}

	public String getBirthstar() {
		return birthstar;
	}

	public void setBirthstar(String birthstar) {
		this.birthstar = birthstar;
	}

	public String getGothra() {
		return gothra;
	}

	public void setGothra(String gothra) {
		this.gothra = gothra;
	}

	@Column
    private Boolean blocked; // Add this field

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photourl;

    @ElementCollection
    @CollectionTable(name = "user_premium", joinColumns = @JoinColumn(name = "userid"))
    @Column(name = "premium_id")
    private List<Integer> premiumPackageIds;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Email> sentEmails;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Email> receivedEmails;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Message> receivedMessages;

    // Default constructor
    public User() {
        super();
        this.role = "user";
        this.blocked = false; // Default value for blocked
    }

    // Full constructor
   

    // Getters and Setters
    public int getUserid() {
        return userid;
    }

    public User(int userid, String firstname, String lastname, String email, String password, String confirmpassword,
			String gender, String mobilenumber, String address, int age, String religion, String caste,
			String maritalstatus, String currentsalary, String occupation, String height, String weight, String dOB,
			String role, String zodiacsign, String birthstar, String gothra, Boolean blocked, byte[] photourl,
			List<Integer> premiumPackageIds, List<Email> sentEmails, List<Email> receivedEmails,
			List<Message> sentMessages, List<Message> receivedMessages) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.gender = gender;
		this.mobilenumber = mobilenumber;
		this.address = address;
		this.age = age;
		this.religion = religion;
		this.caste = caste;
		this.maritalstatus = maritalstatus;
		this.currentsalary = currentsalary;
		this.occupation = occupation;
		this.height = height;
		this.weight = weight;
		DOB = dOB;
		this.role = role;
		this.zodiacsign = zodiacsign;
		this.birthstar = birthstar;
		this.gothra = gothra;
		this.blocked = blocked;
		this.photourl = photourl;
		this.premiumPackageIds = premiumPackageIds;
		this.sentEmails = sentEmails;
		this.receivedEmails = receivedEmails;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
	}

	public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getCurrentsalary() {
        return currentsalary;
    }

    public void setCurrentsalary(String currentsalary) {
        this.currentsalary = currentsalary;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String dOB) {
        this.DOB = dOB;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public byte[] getPhotourl() {
        return photourl;
    }

    public void setPhotourl(byte[] photourl) {
        this.photourl = photourl;
    }

    public List<Integer> getPremiumPackageIds() {
        return premiumPackageIds;
    }

    public void setPremiumPackageIds(List<Integer> premiumPackageIds) {
        this.premiumPackageIds = premiumPackageIds;
    }

    public List<Email> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(List<Email> sentEmails) {
        this.sentEmails = sentEmails;
    }

    public List<Email> getReceivedEmails() {
        return receivedEmails;
    }

    public void setReceivedEmails(List<Email> receivedEmails) {
        this.receivedEmails = receivedEmails;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
}
