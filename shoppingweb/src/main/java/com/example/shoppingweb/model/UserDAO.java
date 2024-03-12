package com.shoppingweb.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<User, String>{

	List<User> findByUserName(String userName);
//	List<User> findByEmployeeID2(String employeeID);
	
    // 使用自動化命名規則進行條件搜尋(多條件)
    List<User> findByUserNameAndPassword(String userName, String password);
	
	User findByEmployeeID(String employeeID);
	
	boolean existsByEmployeeID(String employeeID);
	
}
