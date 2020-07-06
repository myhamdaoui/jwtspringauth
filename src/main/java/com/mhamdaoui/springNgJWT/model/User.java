package com.mhamdaoui.springNgJWT.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank
	@Size(max = 60)
	private String username;
	
	@Column
	@NotBlank
	@Size(max = 60)
	private String password;
	
	@Column
	@NotBlank
	@Size(max = 60)
	private String email;
	
//	@ManyToOne
//	@JoinColumn(name = "training_id")
//	private Training training;
}
