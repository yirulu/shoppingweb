package com.shoppingweb.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService {
	List<User> Users=new ArrayList<>();
	
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
	
	public List<User> getAllUsersByUserNameAndPassword(@PathVariable("userName") String userName,@PathVariable("password") String password){
		return dao.findByUserNameAndPassword(userName, password);	
		
	}

/*	
	public List<User> getAllUsersByEmployeeID2(String employeeID) {
		return dao.findByEmployeeID2(employeeID);
	}
*/
    // 新增使用者
    public User addUser(User user) {
		//資料庫最大會員編號
    	Users = dao.findAll();
    	String max=Users.stream().map(u->u.getEmployeeID()).toList().stream().max(Comparator.comparing(i->i)).get();
    	//編輯訂單編號
    	Integer serialNum = Integer.parseInt(max.substring(1,4))+1;
    	user.setEmployeeID("U"+replenishNum(serialNum.toString(),3));
    	

     	user.setCreatedAt(LocalDateTime.now());
    	user.setUpdatedAt(LocalDateTime.now());
        return dao.save(user);
    }

    // 更新使用者
    public void updateUser(User user) throws Exception{
    	String employeeID=user.getEmployeeID();
    	boolean userExists=dao.existsByEmployeeID(employeeID);
    	if(userExists) {
    		User existingUser=dao.findByEmployeeID(employeeID);
    		existingUser.setDepartment(user.getDepartment());
    		existingUser.setName(user.getName());
    		existingUser.setEmail(user.getEmail());
    		existingUser.setUserName(user.getUserName());
    		existingUser.setPassword(user.getPassword());

     		existingUser.setUpdatedAt( LocalDateTime.now());
    		existingUser.setEditUser(user.getEditUser());
    		existingUser.setEnabled(user.getEnabled());
			dao.save(existingUser);
		}else {
			throw new Exception("User does not exist");
		}   	
     }
    // 刪除使用者
    public void deleteUser(String employeeID) throws Exception {
    	boolean userExists=dao.existsByEmployeeID(employeeID);
		if(userExists) {
			dao.deleteById(employeeID);
		}else {
			throw new Exception("User does not exist");
		}	
     }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//數字長度不足,自動補0
	public static String replenishNum(String inum,Integer NumberDigits)
	{
		while(inum.length()<NumberDigits) {	
		inum="0"+inum;	
		}	
		return inum  ;			
	}
   
    
}    

