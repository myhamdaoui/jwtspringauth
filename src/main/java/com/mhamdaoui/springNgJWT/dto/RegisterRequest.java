package com.mhamdaoui.springNgJWT.dto;

import javax.persistence.Column;

import com.mhamdaoui.springNgJWT.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	private String username;
	private String password;
	private String email;
	private String role;
	private String permissions;
}
