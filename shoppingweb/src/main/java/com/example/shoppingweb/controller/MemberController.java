package com.example.shoppingweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.shoppingweb.model.*;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
	private List<Member> memberList = new ArrayList<>();
	@Autowired
	MemberDAO dao;

	@GetMapping
	public List<Member> getAllMember() {
		return dao.findAll();
	}

	@GetMapping("/id/{id}")
	public Optional<Member> getMemberById(@PathVariable Integer id) {
		return (dao.findById(id));
	}

	@PostMapping()
	public ResponseEntity<?> addMember(@RequestBody Member m) {
		Member ex = dao.findByEmail(m.getEmail());
		if (ex != null) {
			// return ResponseEntity.badRequest().body(new ErrorResponse(false, "此 EMAIL
			// 帳戶已經被使用了"));
			return ResponseEntity.ok().body(new ErrorResponse(false, "此 EMAIL帳戶已經被使用了，請用其他帳號註冊", "duplicate_email"));
		} else {
			dao.save(m);
			return ResponseEntity.ok().body(new SuccessResponse(true, "EMAIL 帳號註冊成功"));
		}
	}

	@PostMapping("/changePassword")
	public ResponseEntity<?> changeMemberPassword(@RequestBody ChangePasswordRequest request) {
		// Find the member by ID
		String memberEmail = request.getEmail();
		Member member = dao.findByEmail(memberEmail);

		// Check if member exists
		if (member == null) {
			return ResponseEntity.badRequest().body(new ErrorResponse(false, "找不到該用戶", "Not_Found_User"));
		}
		
		// Check 身分證號錯誤
		if (!member.getName().equals(request.getNid())) {
			return ResponseEntity.badRequest().body(new ErrorResponse(false, "身分證後五碼錯誤", "Failed_NID"));
		}

		// Check if the old password matches
		if (!member.getPassword().equals(request.getNewPassword())) {
			return ResponseEntity.badRequest().body(new ErrorResponse(false, "舊密碼不正確","Error passworsd"));
		}

		// Update the password to the new password
		member.setPassword(request.getNewPassword());
		dao.save(member);

		return ResponseEntity.ok().body(new SuccessResponse(true, "密碼修改成功"));
	}

	@PutMapping("/email/{email}")
	public Member updateEmployee(@PathVariable("email") String email, @RequestBody Member m) {
		Member ex = dao.findByEmail(email);
		if (m != null) {
			// ex.setMemberid(m.getMemberid());
			ex.setAddress(m.getAddress());
			ex.setBirthday(m.getBirthday());
			ex.setMobile(m.getMobile());
			ex.setEmail(m.getEmail());
			ex.setName(m.getName());
			ex.setNid(m.getNid());
			ex.setActive(true);
			ex.setPassword(m.getPassword());
		}
		System.out.println("update:" + ex);
		return ex;
	}

	@DeleteMapping("/{email}")
	public void deleteMember(@PathVariable String email) {
		Member m = dao.findByEmail(email);
		if (m != null)
			dao.delete(m);
	}

	@PostMapping("/login")
	public ResponseEntity<Member> verifyEmail(@RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session) {
		Member m = dao.findByEmail(email);
		if (m == null) // 找不到 email
			return ResponseEntity.notFound().build(); // 404 NOT Found

		if (m.getPassword().equals(password)) { // 驗證密碼是否正確
			session.setAttribute("loginuser", m);
			return ResponseEntity.ok(m);
		} else {
			return ResponseEntity.badRequest().build(); // "status": 400, "error": "Bad Request"
		}
		/*
		 * 可以用字串比對 Null: 未註冊 404=> register.html Failed: 密碼錯誤。 400=> 忘記密碼
		 * updateMember.html updatePassword.html
		 */
	}

}

class ErrorResponse {
	private boolean success;
	private String message;
	private String errorType;

	public ErrorResponse(boolean success, String message, String errorType) {
		this.success = success;
		this.message = message;
		this.errorType = errorType;
	}

	public ErrorResponse() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

class SuccessResponse {

	private boolean success;
	private String message;

	public SuccessResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public SuccessResponse() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
class ChangePasswordRequest {
    private String Email;
    private String Nid;
    private String newPassword;
	public ChangePasswordRequest() {	}
	public ChangePasswordRequest(String email, String nid, String Password) {
		super();
		Email = email;
		Nid = nid;
		this.newPassword = Password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getNid() {
		return Nid;
	}
	public void setNid(String nid) {
		Nid = nid;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
        
}