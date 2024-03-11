package com.shoppingweb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shoppingweb.model.*;

@RestController
public class UserController {
	@Autowired
	UserService srv;
	
	//手動執行http://localhost:8080/users

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return srv.getAllUsers();
	}
//手動執行 http://localhost:8080/users/userName?userName=aa
	@GetMapping("/users/userName")
	public List<User> getAllUsersByUserName(@RequestParam String userName) {
		return srv.getAllUsersByUserName(userName);
	}
/*
	//手動執行 http://localhost:8080/users/employeeID?employeeID=u001
	@GetMapping("/users/employeeID")
	public List<User> getAllUsersByEmployeeID(@RequestParam String employeeID) {
		return srv.getAllUsersByEmployeeID(employeeID);
	}
*/

	/*手動執行 http://localhost:8080/users/add
	 * 
 {
        "employeeID": "u003",
        "department": null,
        "name": null,
        "email": null,
        "userName": "bb",
        "password": "bb",
        "createdAt": null,
        "createdUser": null,
        "updatedAt": null,
        "editUser": null,
        "enabled": null
    }
	 * */

	@PostMapping("/users/add")
	public ResponseEntity<String> addUser(@RequestBody User user ){
		try {
			srv.addUser(user);
			return ResponseEntity.ok("User added successfully");		
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("User ID is not unique");
		}			
	}
	
	@PostMapping("/users/update")
	public ResponseEntity<String> updateUser(@RequestBody  User user  ){
		try {
			srv.updateUser(user);
            return ResponseEntity.ok("User updated successfully"); 	
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("User ID is not unique");
		}
                     
	}	
	
}
