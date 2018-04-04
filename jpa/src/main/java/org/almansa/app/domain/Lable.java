package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Lable {
	@Id
	@GeneratedValue
	private Long Id;
	
	@NonNull
	@Column(name="agency_company_name")
	private String name;

	public Lable(Long id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	public Lable() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void changeName(String name) {
		this.name = name;
	}
}
