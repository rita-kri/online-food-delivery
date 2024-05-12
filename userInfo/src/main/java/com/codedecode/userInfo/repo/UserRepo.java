package com.codedecode.userInfo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.userInfo.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	

}
