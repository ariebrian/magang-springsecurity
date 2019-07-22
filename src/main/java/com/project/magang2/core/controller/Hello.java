package com.project.magang2.core.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	@PreAuthorize("hasAuthority('MGR_A0_PST') or hasAuthority('HRD_A0_PST')")
//	@PreAuthorize("hasRole('user')")
	public String hello() {
		String a = "manager";
		return a;
	}
	
//	@PreAuthorize("hasRole('admin')")
	@RequestMapping(method = RequestMethod.GET, value = "/hai")
	@PreAuthorize("hasAuthority('HRD_A0_PST')")
	
	public String hai() {
		String a = "hrd";
		return a;
	}
}
