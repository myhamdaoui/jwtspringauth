package com.mhamdaoui.springNgJWT.api.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mhamdaoui.springNgJWT.dao.RoleRepository;
import com.mhamdaoui.springNgJWT.dao.UserRepository;
import com.mhamdaoui.springNgJWT.dto.AuthRequest;
import com.mhamdaoui.springNgJWT.dto.AuthResponse;
import com.mhamdaoui.springNgJWT.dto.ErrorDto;
import com.mhamdaoui.springNgJWT.dto.RegisterRequest;
import com.mhamdaoui.springNgJWT.dto.RegisterResponse;
import com.mhamdaoui.springNgJWT.model.Role;
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
	private RoleRepository roleRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/admin")
	public String welcome() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		
		return "Admin page";
	}
	
	@GetMapping("/processes")
	public String index() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		
		return "BA & Admin page";
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
		User user = new User();

		System.out.println(registerRequest);
		
		// Find userRole by name (ROLE_ADMIN OR ROLE_BA)
		Role userRole = roleRepository.findByName(registerRequest.getRole());
		
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setRole(userRole);
		user.setPermissions(registerRequest.getPermissions());
		
		userRepository.save(user);
		
		return ResponseEntity.ok(new RegisterResponse("SIGNUP_SUCCESS"));
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> auth(@Valid @RequestBody AuthRequest authRequest) throws Exception {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
		);
		
		String token = jwtUtil.generateToken(authRequest.getUsername());
		
		AuthResponse response = new AuthResponse();
		response.setToken(token);
		
		return ResponseEntity.ok(response);
	}
}
