package com.project.magang2.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//import model
import com.project.magang2.core.model.LoginModel;

public interface LoginRepository extends CrudRepository<LoginModel,String> {
//query
	@Query("SELECT u FROM LoginModel u WHERE u.username = ?1")
	LoginModel findByUsername(String username);
}
