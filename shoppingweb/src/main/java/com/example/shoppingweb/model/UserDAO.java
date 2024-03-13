package com.shoppingweb.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserDAO extends JpaRepository<User, String>{

	List<User> findByUserName(String userName);
//	List<User> findByEmployeeID2(String employeeID);
	
    // 使用自動化命名規則進行條件搜尋(多條件)
    List<User> findByUserNameAndPassword(String userName, String password);
	
	User findByEmployeeID(String employeeID);
	
	boolean existsByEmployeeID(String employeeID);

    // 自定義SQL查詢
    @Query(value = "select * from user where employeeID = ?1", nativeQuery = true)
    User queryByEmployeeID(String employeeID);
	
}
