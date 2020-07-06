package com.mhamdaoui.springNgJWT;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mhamdaoui.springNgJWT.dao.UserRepository;
import com.mhamdaoui.springNgJWT.model.User;

@SpringBootApplication
public class SpringNgJwtApplication {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void usersSeeder() {
		userRepository.save(new User(null, "hamdaoui", passwordEncoder.encode("1234"), "sadasds@gmail.com"));
		userRepository.save(new User(null, "anas", passwordEncoder.encode("1234"), "sadasds@gmail.com"));
		userRepository.save(new User(null, "ahmed", passwordEncoder.encode("1234"), "sadasds@gmail.com"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringNgJwtApplication.class, args);
	}

}
