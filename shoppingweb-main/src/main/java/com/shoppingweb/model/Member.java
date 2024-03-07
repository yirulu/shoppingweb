package com.shoppingweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="members")
@Data
@NoArgsConstructor
@AllArgsConstructor	
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="memberid", unique=true)
	private String memberid;
	
	@Column(name="email", unique=true)
	private String email;
	private String name;
	private String password;
	private String address;
	private String mobile;
	private String birthday;
	private String nid;
	private Boolean active; //是否啟用
	
	@Override
	public String toString() {
		return "Member [memberid=" + memberid + ", email=" + email + ", password=" + password + ", address=" + address
				+ ", mobile=" + mobile + ", birthday=" + birthday + ", nid=" + nid + ", active=" + active + "]";
	}
	

}
