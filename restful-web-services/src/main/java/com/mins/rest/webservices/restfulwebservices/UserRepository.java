package com.mins.rest.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mins.rest.webservices.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	

}
