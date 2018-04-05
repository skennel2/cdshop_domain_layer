package org.almansa.app.domain;

import javax.persistence.*;
import javax.persistence.Entity;

import org.springframework.lang.NonNull;

@Entity
public class Genre extends EntityBase{
	
	@NonNull		
	@Column(name="genre_name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Genre [name=" + name + "]";
	}		
}