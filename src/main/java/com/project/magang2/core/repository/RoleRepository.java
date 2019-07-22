package com.project.magang2.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.project.magang2.core.model.LoginModel;
import com.project.magang2.core.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel,Long> {
//	RoleModel findById(int id);
}
