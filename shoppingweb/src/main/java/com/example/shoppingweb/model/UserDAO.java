package com.example.shoppingweb.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserDAO extends JpaRepository<User, String>{

	List<User> findByUserName(String userName);
	//List<User> findByEmployeeID(String employeeID);
	
	User findByEmployeeID(String employeeID);
	
	boolean existsByEmployeeID(String employeeID);
	
}
