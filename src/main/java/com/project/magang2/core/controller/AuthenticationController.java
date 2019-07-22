package com.project.magang2.core.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.magang2.core.model.LoginModel;
import com.project.magang2.core.security.JwtTokenUtil;
import com.project.magang2.core.service.UserDetailsServiceImpl;
import static com.project.magang2.core.security.SecurityConstants.HEADER_STRING;
import static com.project.magang2.core.security.SecurityConstants.TOKEN_PREFIX;

@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil tokenUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody LoginModel logModel, HttpServletResponse res) throws AuthenticationException{
		final Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
								logModel.getUsername(),
								logModel.getPassword(),
								new ArrayList<>()
					)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
        final LoginModel user = userDetailsService.findOne(logModel.getUsername());
		final String token = tokenUtil.generateToken(user,authentication);
		res.addHeader(HEADER_STRING,TOKEN_PREFIX+""+token);
		return token;
	}

}
