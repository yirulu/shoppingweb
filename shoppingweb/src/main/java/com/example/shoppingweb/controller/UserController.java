package com.shoppingweb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingweb.model.*;

@RestController
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserService srv;
	
	//手動執行http://localhost:8080/users
	//查詢全部使用者
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return srv.getAllUsers();
	}
	
	//手動執行 http://localhost:8080/users/bb
	//【使用者帳戶+密碼】查使用者個人資料
	@GetMapping("/users/{userName}")
	public List<User> getAllUsersByUserName(@PathVariable String userName) {
		return srv.getAllUsersByUserName(userName);
	}

	
	//手動執行 http://localhost:8080/users/login/zz/zz
	//【使用者帳戶+密碼】查使用者個人資料
	@GetMapping("/users/login/{userName}/{password}")
	public List<User> getAllUsersByUserNameAndPassword(@PathVariable("userName") String userName,@PathVariable("password") String password){
		return srv.getAllUsersByUserNameAndPassword(userName, password);	
		
	}

	/*
	//手動執行 http://localhost:8080/users/employeeID?employeeID=u001
	@GetMapping("/users/employeeID")
	public List<User> getAllUsersByEmployeeID2(@RequestParam String employeeID) {
		return srv.getAllUsersByEmployeeID2(employeeID);
	}
*/

	/*手動執行 http://localhost:8080/users
	 * 
  {
        "department": "E",
        "name": "員工11",
        "email": "gg1@gmail.com.tw",
        "userName": "gg1",
        "password": "gg1",
        "enabled": true
    }	 * */
	//新增使用者
	@PostMapping("/users")
	public ResponseEntity<String> addUser(@RequestBody User user ){
		try {
			srv.addUser(user);
			return ResponseEntity.ok("User added successfully");		
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("User ID is not unique");
		}			
	}
	
	//修改使用者訊息
	@PostMapping("/users/{employeeID}")
	public ResponseEntity<String> updateUser(@PathVariable String employeeID,@RequestBody  User user  ){
		try {
			srv.updateUser(user);
            return ResponseEntity.ok("User updated successfully"); 	
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("User ID is not unique");
		}
                     
	}	
	
	//刪除使用者訊息
	@DeleteMapping("/users/{employeeID}")
	public ResponseEntity<String> deleteProductBtId(@PathVariable String employeeID){
		try {
			srv.deleteUser(employeeID);
			return ResponseEntity.ok("User deleted successfully");
		}catch (Exception e){
			return ResponseEntity.badRequest().body("Error deleting User:"+e.getMessage());
		}
		
	
		
	}	
}
