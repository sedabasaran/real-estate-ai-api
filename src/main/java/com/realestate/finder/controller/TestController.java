package com.realestate.finder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.finder.security.JwtUtil;

@RestController
@RequestMapping("/api/test")
public class TestController {

	private final JwtUtil jwtUtil;

	public TestController(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@GetMapping("/generate-token")
	public ResponseEntity<String> generateToken() {
		String token = jwtUtil.generateToken("testuser@example.com");
		return ResponseEntity.ok(token);
	}
}
