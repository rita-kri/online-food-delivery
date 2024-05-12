package com.codedecode.userInfo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.userInfo.dto.UserDto;
import com.codedecode.userInfo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto savedUser = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/allUsers")
		public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto>allUsers = this.userService.findAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
		}
	@GetMapping("/fetchById/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
		return this.userService.getUserById(id);
	
	}
	
}

