package com.brightcoding.app.ws.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brightcoding.app.ws.entities.UserEntity;
import com.brightcoding.app.ws.repositories.UserRepository;
import com.brightcoding.app.ws.services.UserService;
import com.brightcoding.app.ws.shared.Utils;
import com.brightcoding.app.ws.shared.dto.AddressDto;
import com.brightcoding.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils utils;

	@Override
	public UserDto createUser(UserDto user) {

		UserEntity checkUser = userRepository.findByEmail(user.getEmail());

		if (checkUser != null)
			throw new RuntimeException("Email Alredy Exists");

		/*
		 * UserEntity userEntity = new UserEntity();
		 * 
		 * BeanUtils.copyProperties(user, userEntity);
		 *
		 * userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(user.getPassword
		 * ())); userEntity.setUserId(utils.generateStringId(32)); UserEntity userSaved
		 * = userRepository.save(userEntity); UserDto userDto = new UserDto();
		 *
		 * BeanUtils.copyProperties(userSaved, userDto);
		 *
		 */
		
		
		ModelMapper modelMapper = new ModelMapper();

		for (int i = 0; i < user.getAdresses().size(); i++) {
			AddressDto adresses = user.getAdresses().get(i);
			adresses.setUser(user);
			adresses.setAddressId(utils.generateStringId(20));
			
			user.getAdresses().set(i,adresses);
		}
		user.getContact().setUser(user);
		user.getContact().setContactId(utils.generateStringId(20));
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		
		userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(user.getPassword()));

		userEntity.setUserId(utils.generateStringId(32));

		UserEntity UserSaved = userRepository.save(userEntity);

		UserDto userDto = modelMapper.map(UserSaved, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedpassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserById(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
		
//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		UserEntity userUpdated = userRepository.save(userEntity);
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(userUpdated, dto);

		return dto;
	}

	@Override
	public void deleteUser(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null) {
			throw new UsernameNotFoundException("user not found : " + userId);
		}
		userRepository.delete(userEntity);

	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		if (page > 0)
			page -= page;

		List<UserDto> users = new ArrayList<>();
		PageRequest pageable = PageRequest.of(page, limit);
		
		Page<UserEntity> usersEntities = userRepository.findAllUserByFirstName(pageable);

		ModelMapper modelMapper = new ModelMapper();
		
		for (UserEntity user : usersEntities) {
			UserDto dto = modelMapper.map(user, UserDto.class);
			users.add(dto);
		}
		
		return users;
	}

}
