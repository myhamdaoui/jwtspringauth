package com.mhamdaoui.springNgJWT;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mhamdaoui.springNgJWT.dao.RoleRepository;
import com.mhamdaoui.springNgJWT.dao.UserRepository;
import com.mhamdaoui.springNgJWT.model.Role;
import com.mhamdaoui.springNgJWT.model.User;

@SpringBootApplication
public class SpringNgJwtApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void usersSeeder() {
		Role admin = new Role();
		admin.setName("ROLE_ADMIN");
		
		Role ba = new Role();
		ba.setName("ROLE_BA");
		
		System.out.println(admin);
		roleRepository.save(admin);
		roleRepository.save(ba);
		
		userRepository.save(new User(null, "hamdaoui", passwordEncoder.encode("12345678"), "sadasds@gmail.com", admin, "CRUD,APPROVE"));
		userRepository.save(new User(null, "anas", passwordEncoder.encode("12345678"), "sadasds2@gmail.com", ba, "CRUD"));
		userRepository.save(new User(null, "ahmed", passwordEncoder.encode("1234"), "sadasds3@gmail.com", ba, "CRUD"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringNgJwtApplication.class, args);
	}

}
