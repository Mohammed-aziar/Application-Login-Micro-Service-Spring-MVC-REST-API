package com.brightcoding.app.ws.shared.dto;

import java.io.Serializable;

public class ContactDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2684724566657401061L;

	private Long Id;
	private String  contactId;
	private String mobile;
	private String skype;
	private UserDto user;
	
	
	
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
	
}
