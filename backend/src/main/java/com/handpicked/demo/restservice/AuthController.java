package com.handpicked.demo.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.handpicked.demo.domain.User;
import com.handpicked.demo.payload.SigninRequest;
import com.handpicked.demo.payload.SignupRequest;
import com.handpicked.demo.service.UserService;
import com.handpicked.demo.util.JwtUtils;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		User user = userService.getByEmail(signupRequest.getEmail());
		
		if (user != null) {
			return ResponseEntity.badRequest().body("There's already an account using this e-mail");
		} else {
			User newUser = new User(signupRequest.getEmail(), signupRequest.getPassword());
			userService.save(newUser);
			return ResponseEntity.ok("Success");
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) throws Exception {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword())
			);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body("Invalid Username/Password!");
		}
		
		return ResponseEntity.ok(jwtUtils.generateToken(signinRequest.getEmail()));
	}
}
