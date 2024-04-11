package com.codedecode.userInfo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codedecode.userInfo.dto.UserDto;
import com.codedecode.userInfo.entity.UserEntity;
import com.codedecode.userInfo.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;

	public UserDto createUser(UserDto userDto) {
		UserEntity dtoToUserEntity = this.dtoToUserEntity(userDto);
		UserEntity savedUser = this.userRepo.save(dtoToUserEntity);
		return this.userToDto(savedUser);
	}
	
	public List<UserDto> findAllUsers() {
		List<UserEntity> users = this.userRepo.findAll();
		List<UserDto> listOfUsers = users.stream().map(user ->userToDto(user)).collect(Collectors.toList());
		return listOfUsers;
	}

	public ResponseEntity<UserDto> getUserById(Integer id) {
		Optional<UserEntity> findUserById = this.userRepo.findById(id);
		if(findUserById.isPresent()) {
			return new ResponseEntity<UserDto>(userToDto(findUserById.get()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		
	}

	
	public UserEntity dtoToUserEntity(UserDto userDto) {
		UserEntity mapToEntity = this.modelMapper.map(userDto, UserEntity.class);
//		mapToEntity.setUserId(userDto.getUserId());
//		mapToEntity.setUserName(userDto.getUserName());
//		mapToEntity.setUserPassword(userDto.getUserPassword());
//		mapToEntity.setAddress(userDto.getAddress());
//		mapToEntity.setCity(userDto.getCity());
		
		return mapToEntity;
	}
	
	public UserDto userToDto(UserEntity userEntity) {
		UserDto entityToDto = this.modelMapper.map(userEntity, UserDto.class);
//		entityToDto.setUserId(userEntity.getUserId());
//		entityToDto.setUserName(userEntity.getUserName());
//		entityToDto.setUserPassword(userEntity.getUserPassword());
//		entityToDto.setAddress(userEntity.getAddress());
//		entityToDto.setCity(userEntity.getCity());
		
		return entityToDto;
	}

	
}
