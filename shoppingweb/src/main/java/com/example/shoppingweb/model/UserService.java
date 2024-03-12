package com.example.shoppingweb.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
    @Autowired
    private UserDAO dao;

    // 取得分頁使用者資料
    public Page<User> getUsersPageable(int page) {
        Pageable pageable = PageRequest.of(page, 5); // 每頁顯示 5 筆資料
        return dao.findAll(pageable);
    }

	public List<User> getAllUsers() {
		return dao.findAll();
	}

	public List<User> getAllUsersByUserName(String userName) {
		return dao.findByUserName(userName);
	}
/*	
	public List<User> getAllUsersByEmployeeID(String employeeID) {
		return dao.findByEmployeeID(employeeID);
	}
*/
    // 新增使用者
    public User addUser(User user) {
        return dao.save(user);
    }

    // 更新使用者
    public void updateUser(User user) throws Exception{
    	String employeeID=user.getEmployeeID();
    	boolean userExists=dao.existsByEmployeeID(employeeID);
    	if(userExists) {
    		User existingUser=dao.findByEmployeeID(employeeID);
    		existingUser.setUserName(user.getUserName());
    		existingUser.setPassword(user.getPassword());
    		existingUser.setEditUser(user.getEditUser());
    		existingUser.setEmail(user.getEmail());
			dao.save(existingUser);
		}else {
			throw new Exception("User does not exist");
		}   	
     }
    // 刪除使用者
    public void deleteEmployee(String employeeID) {
    	dao.deleteById(employeeID);;
    }
	
}    

