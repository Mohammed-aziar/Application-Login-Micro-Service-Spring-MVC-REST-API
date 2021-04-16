package com.brightcoding.app.ws.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brightcoding.app.ws.exceptions.UserException;
import com.brightcoding.app.ws.requests.UserRequest;
import com.brightcoding.app.ws.responses.ErrorMessage;
import com.brightcoding.app.ws.responses.UserResponse;
import com.brightcoding.app.ws.services.UserService;
import com.brightcoding.app.ws.shared.dto.UserDto;



@RestController
@RequestMapping("/users") // Localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;
	

		@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
		public List<UserResponse> getAllUsers(@RequestParam(value="page",defaultValue = "1") int page,@RequestParam(value="limit",defaultValue = "2") int limit){
			List<UserResponse> userResponses=new ArrayList<>();
			
			List<UserDto> userDto=userService.getUsers(page,limit);
			ModelMapper modelMapper=new ModelMapper();
			for(UserDto dto : userDto) {
				UserResponse user = modelMapper.map(dto, UserResponse.class);
				//BeanUtils.copyProperties(dto, user);
				userResponses.add(user);
				
			}
			return userResponses;
		}
	
	
	@GetMapping(path ="/{id}",
				produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<UserResponse> getUser(@PathVariable("id") String id) {
//		UserDto userdto = userService.getUserById(id);
//		UserResponse userResponse=new UserResponse();
//		BeanUtils.copyProperties(userdto, userResponse);
		UserDto userdto = userService.getUserById(id);
		ModelMapper modelMapper = new ModelMapper();
		UserResponse userResponse=modelMapper.map(userdto, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping(	consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
					produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
				)
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid  UserRequest userRequest) throws Exception{
		
		if(userRequest.getEmail().isEmpty() || userRequest.getFirstName().isEmpty() || userRequest.getLastName().isEmpty() || userRequest.getPassword().isEmpty()) throw new UserException(ErrorMessage.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		//UserDto userDto=new UserDto();
		//BeanUtils.copyProperties(userRequest, userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		
		UserDto createUser = userService.createUser(userDto);
		
		/*
		 * UserResponse userResponse = new UserResponse();
		 * 
		 * BeanUtils.copyProperties(createUser, userResponse);
		 * 
		 * */
		
		UserResponse userResponse=modelMapper.map(createUser, UserResponse.class);
		
		
		
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
	}
	
	@PutMapping(path = "{id}",
			consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id,@RequestBody UserRequest userRequest) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userRequest, userDto);
			UserDto updateUser = userService.updateUser(id,userDto);
			UserResponse userResponse=new UserResponse();
			BeanUtils.copyProperties(updateUser, userResponse);
		
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
