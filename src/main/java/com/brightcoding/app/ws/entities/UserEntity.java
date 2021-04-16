package com.brightcoding.app.ws.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity(name = "users")
public class UserEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6371470489253542303L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(length =50,nullable = false)
	private String userId;
	
	@Column(nullable = false , length = 50)
	private String firstName;
	
	@Column(nullable = false ,length = 50)
	private String lastName;
	
	@Column(nullable = false , length = 120,unique = true)
	private String email;
	
	@Column(nullable = false)
	private String encryptedpassword;
	
	@Column(nullable = true)
	private String emailVerificationToken;
	
	@Column( nullable = false)
	private Boolean emailVerificationStatus =false;
	
	@OneToMany(mappedBy = "User" ,cascade = CascadeType.ALL)
	private List<AddressEntity> adresses;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private ContactEntity contact;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user")
	private Set<GroupEntity> groups=new HashSet<>();
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEncryptedpassword() {
		return encryptedpassword;
	}
	public void setEncryptedpassword(String encryptedpassword) {
		this.encryptedpassword = encryptedpassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public List<AddressEntity> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<AddressEntity> adresses) {
		this.adresses = adresses;
	}
	public ContactEntity getContact() {
		return contact;
	}
	public void setContact(ContactEntity contact) {
		this.contact = contact;
	}
	
}
