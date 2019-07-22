package com.project.magang2.core.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.magang2.core.model.LoginModel;
import com.project.magang2.core.repository.LoginRepository;

@RestController
public class UserController {
	private LoginRepository loginRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserController(LoginRepository loginRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.loginRepo = loginRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/sign-up")
	public void signUp(@RequestBody LoginModel user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		System.out.println(user.getUsername());
//		System.out.println(user.getPassword());
//		System.out.println(user.getRoles().getId());
//		System.out.println(user.getActor().getId());

		loginRepo.save(user);
	}
}
