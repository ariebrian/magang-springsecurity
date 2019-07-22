package com.project.magang2.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ar_role")
public class RoleModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="role")
	private String role;
	
	public RoleModel() {
		super();
	}
	public RoleModel(int id) {
		super();
		this.id = id;
	}
	public RoleModel(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
}
