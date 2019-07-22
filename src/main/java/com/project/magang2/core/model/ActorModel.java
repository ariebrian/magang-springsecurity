package com.project.magang2.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actor")
public class ActorModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "nama")
	private String nama;
	
	
	public ActorModel() {
		super();
	}
	public ActorModel(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
}
