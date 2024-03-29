package com.greenrent.dto;

import java.util.HashSet;
import java.util.Set;

import com.greenrent.domain.Role;
import com.greenrent.domain.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	
	private Long id;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
	private String zipCode;
	
	private Boolean builtIn;
	
	private Set<String> roles;
	

	
	public void setRoles(Set<Role> roles) {
		Set<String> roleStr=new HashSet<>();
		
		roles.forEach(r->{
			if(r.getName().equals(RoleType.ROLE_ADMIN)) {
				roleStr.add("Administrator");
			}else {
				roleStr.add("Customer");
			}
		});
		this.roles=roleStr;
	}

	



}
