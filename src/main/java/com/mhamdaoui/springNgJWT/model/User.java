package com.mhamdaoui.springNgJWT.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
	@NotNull
	@Size(min = 3, max = 20, message = "username should contains 3 to 20 characters")
	private String username;
	
	@Column
	@NotNull
	private String password;
	
	@Column
	@NotNull
	@Email
	private String email;
	
	@ManyToOne()
	@JoinColumn(name="role_id")
    private Role role;

    private String permissions;

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
	
//	@ManyToOne
//	@JoinColumn(name = "training_id")
//	private Training training;
}
