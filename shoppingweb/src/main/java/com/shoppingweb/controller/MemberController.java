package com.shoppingweb.controller;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shoppingweb.model.Member;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/member")
public class MemberController {
	private List<Member> memberList = new ArrayList<>();

    public MemberController() {
    	memberList.add(new Member("M24030501","n11@xx.com","AAA","111111","taipei","0911111111","19880101","11111",false));
    	memberList.add(new Member("M24030502","n22@xx.com","BBB","222222","taichung","0922222222","19980202","22222",false));
    	memberList.add(new Member("M24030503","n33@xx.com","CCC","333333","kaohsiung","0933333333","19700303","33333",false));
    }

	@GetMapping
	public List<Member> getAllMember() {
		return memberList;
	}

	@GetMapping("/id/{id}")
	public Member getMemberById(@PathVariable String id) {
		for (Member member : memberList) {
			if (member.getMemberid().equals(id)) {
				return member;
			}
		}
		return null;
	}

	@GetMapping("/email/{email}")
	public Member getMemberByEmail(@PathVariable String email) {
		for (Member member : memberList) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}
		return null;
	}
	
	@GetMapping("/name/{name}")
	public Member getMemberByName(@PathVariable String name) {
		for (Member member : memberList) {
			if (member.getName().equals(name)) {
				return member;
			}
		}
		return null;
	}

	@PostMapping
	public Member createMember(@RequestBody Member member) {
		memberList.add(member);
		return member;
	}

	@PutMapping("/email/{email}")
	public Member updateMember(@PathVariable String email, @RequestBody Member updatedMember) {
		for (int i = 0; i < memberList.size(); i++) {
			Member member = memberList.get(i);
			if (member.getEmail().equals(email)) {
				memberList.set(i, updatedMember);
				return updatedMember;
			}
		}
		return null;
	}

	@DeleteMapping("/{email}")
	public Member deleteMember(@PathVariable String email) {
		for (Member member : memberList) {
			if (member.getEmail().equals(email)) {
				memberList.remove(member);
				return member;
			}
		}
		return null;
	}
}





