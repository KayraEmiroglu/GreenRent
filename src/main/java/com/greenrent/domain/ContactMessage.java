package com.greenrent.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="tbl_cmesage")
public class ContactMessage implements Serializable{
	/**
	 * bu interface'i java yoluyla network'te dolaştırmak istediğimizde
	 * Serializable kullanılırz.
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 1,max = 50,message = "Your Name '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message = "Please provide your name")
	@Column(length = 50,nullable = false)
	private String name;
	
	@Size(min = 5,max = 50,message = "Your Subject '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message = "Please provide your message subject")
	@Column(length = 50,nullable = false)
	private String subject;
	
	@Size(min = 20,max = 200,message = "Your  messaage body '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message = "Please provide your message Body")
	@Column(length = 50,nullable = false)
	private String body;
	
	@Email
	@Column(length = 50,nullable = false)
	private String email;
	

}
