package com.project.magang2.core.service;

import com.project.magang2.core.model.LoginModel;

public interface ServiceInterface {
//	LoginModel auth(String username, String password);
	
	LoginModel findOne(String username);
}
