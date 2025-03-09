package com.srivasavi.boot_intro.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "table_student")
public class Student {
	
	@Id
	@Column(name = "s_id")
	private String studentId;
//	@Column(nullable = false)
//	@Size(min = 3, message = "Size should not be less than 9")
	private String password;
	@NotNull
	private String name;
	private String role;

}