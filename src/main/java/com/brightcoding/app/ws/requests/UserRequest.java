package com.brightcoding.app.ws.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	@NotBlank(message = "ce champ doit etre null !")
	@NotNull
	@Size(min = 3, message = "doit avoir au moins 3 caractres")
	private String firstName;
	@NotNull(message = "ce champ doit etre null !")
	@Size(min = 3, message = "doit avoir au moins 3 caractres")
	private String lastName;
	@NotNull(message = "ce champ doit etre null !")
	@Email(message = "ce champ doit respecter le format email !")
	private String email;
	@NotNull(message = "ce champ doit etre null !")
	@Size(min = 8, message = "mot de passe doit avoir au moin 8 caratres")
	@Size(max = 12, message = "mot de passe doit avoir au max 12 caratres")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message = "Un mot de passe doit contenir au minimum 8 caractères, à savoir : au moins une lettre minuscule et une lettre majuscule, un caractère spécial et un chiffre")
	private String password;

	private List<AddressRequest> adresses;
	
	private ContactRequest contact;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AddressRequest> getAdresses() {
		return this.adresses;
	}

	public void setAdresses(List<AddressRequest> adresses) {
		this.adresses = adresses;
	}

	public ContactRequest getContact() {
		return contact;
	}

	public void setContact(ContactRequest contact) {
		this.contact = contact;
	}

}
