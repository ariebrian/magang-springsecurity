package com.project.magang2.core.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="actor_access")
public class LoginModel {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@OneToOne
	@JoinColumn(name = "user_id")
	private ActorModel actor;
	@OneToOne
	@JoinColumn(name = "org_id")
	private OrganizationModel org;
	@OneToOne
	@JoinColumn(name = "role_id")
	private RoleModel roles;
	
	public LoginModel() {
		super();
	}
	
	public LoginModel(String username, String password, ActorModel actor, OrganizationModel org, RoleModel roles) {
		this.username = username;
		this.password = password;
		this.actor = actor;
		this.org = org;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ActorModel getActor() {
		return actor;
	}

	public void setActor(ActorModel actor) {
		this.actor = actor;
	}

	public OrganizationModel getOrg() {
		return org;
	}

	public void setOrg(OrganizationModel org) {
		this.org = org;
	}

	public RoleModel getRoles() {
		return roles;
	}

	public void setRoles(RoleModel roles) {
		this.roles = roles;
	}
	
	
}
