package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Artist extends EntityBase{

	@NonNull
	@Column(name="artist_name")
	private String name;

	@ManyToOne
	@JoinColumn(name="agency_company_id")
	private Lable lable;

	public String getName() {
		return name;
	}

	public void changeName(String name) {
		this.name = name;
	}
	
	public Lable getLable() {
		return lable;
	}

	public void changeLable(Lable lable) {
		this.lable = lable;
	}

	@Override
	public String toString() {
		return "Artist [name=" + name + ", lable=" + lable + "]";
	}
}