package com.mhamdaoui.springNgJWT.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mhamdaoui.springNgJWT.dao.UserRepository;
import com.mhamdaoui.springNgJWT.dto.AuthRequest;
import com.mhamdaoui.springNgJWT.dto.RegisterRequest;
import com.mhamdaoui.springNgJWT.model.User;
import com.mhamdaoui.springNgJWT.util.JwtUtil;

@RestController
@CrossOrigin
public class AuthController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/admin")
	public String welcome() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		
		return "Welcome to Novelis";
	}
	
	@GetMapping("/")
	public String index() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		
		return "Welcome to home page";
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		
		userRepository.save(user);
		
		return new ResponseEntity("SIGNUP_SUCCESS", HttpStatus.OK);
	}
	
	@PostMapping("/auth")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
			);
		} catch(Exception ex) {
			throw new Exception("Invalid username/password");
		}
		
		return jwtUtil.generateToken(authRequest.getUsername());
	}
}
