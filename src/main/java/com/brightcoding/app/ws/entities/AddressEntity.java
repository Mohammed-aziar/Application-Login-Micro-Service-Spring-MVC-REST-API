package com.brightcoding.app.ws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "addresses")
public class AddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8106560824187969451L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(length =50,nullable = false)
	public String addressId;
	
	@Column(length =30, nullable = false)
	public String city;
	
	@Column(length = 50, nullable = false)
	public String country;
	
	@Column(length = 50, nullable = false)
	public String street;
	
	@Column(length =7, nullable = false)
	public String postal;
	
	@Column(length =20, nullable = false)
	public String type;
	
	@ManyToOne
	@JoinColumn(name = "user_Id")
	public UserEntity User;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserEntity getUser() {
		return User;
	}

	public void setUser(UserEntity user) {
		User = user;
	}

	

	

}
