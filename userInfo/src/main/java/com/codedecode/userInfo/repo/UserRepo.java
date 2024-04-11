package com.codedecode.userInfo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.userInfo.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	

}
