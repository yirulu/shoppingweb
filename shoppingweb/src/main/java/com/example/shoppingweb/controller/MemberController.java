package com.example.shoppingweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;

import com.example.shoppingweb.model.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
	//List<Member> members = new ArrayList<>();

	@Autowired
	MemberDAO dao;
	
	@GetMapping("")
	public List<Member> getAllMembers(){
	     return dao.findAll();	
	}
	@GetMapping("/{id}")
	public Member findMemberById(@PathVariable("id") Integer id){
		return dao.findById(id).orElse(null);	
	}
	@GetMapping("/email/{email}")
	public Member findMemberByEmail(@PathVariable("email") String email){
		return dao.findByEmail(email);
	}
	// 創建新會員
    @PostMapping
    public Member createMember(@RequestBody Member newMember){
        Member member=new Member();
        
        member.setName(newMember.getName());
        member.setEmail(newMember.getEmail());
        member.setPassword(newMember.getPassword());
        member.setAddress(newMember.getAddress());
        member.setMobile(newMember.getMobile());
        member.setBirthday(newMember.getBirthday());
        member.setNid(newMember.getNid());
        member.setActive(true);

        return dao.save(member);
    }
	
	@PutMapping("/{id}")
	public Member updateMemberById(@PathVariable Integer id, @RequestBody Member updatedMember){
        Member existingMember = dao.findById(id).orElse(null);
        if(existingMember != null){
            existingMember.setName(updatedMember.getName());
            existingMember.setEmail(updatedMember.getEmail());
            existingMember.setPassword(updatedMember.getPassword());
            existingMember.setAddress(updatedMember.getAddress());
            existingMember.setMobile(updatedMember.getMobile());
            existingMember.setBirthday(updatedMember.getBirthday());
            existingMember.setNid(updatedMember.getNid());

            return dao.save(existingMember);
        }
        return null;
    }
	@DeleteMapping("/{id}")
	public void delMember(@PathVariable Integer id)
	{
		Member existingMember = dao.findById(id).orElse(null);
		if(existingMember != null)
			dao.delete(existingMember);
	}
	
	
	
}
