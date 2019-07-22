//package com.project.magang.core.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.project.magang.core.model.LoginModel;
//import com.project.magang.core.repository.LoginRepository;
//import com.project.magang.core.service.ServiceInterface;
//
//@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//public class LoginService implements ServiceInterface{
//	@Autowired
//	private LoginRepository login;
//	
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//	public LoginModel auth(String username, String password) {
//		// TODO Auto-generated method stub
//		return login.findByUsername(username);
//	}
////return query
//	
//}
