package com.brightcoding.app.ws.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.brightcoding.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto dto);
	UserDto getUser(String email);
	UserDto getUserById(String userId);
	UserDto updateUser(String id,UserDto userDto );
	void deleteUser(String userId);
	
	List<UserDto> getUsers(int page,int limit);
}
