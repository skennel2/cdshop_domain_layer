package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Artist {
	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	@Column(name="artist_name")
	private String name;

	@ManyToOne
	@JoinColumn(name="agency_company_id")
	private Lable lable;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Artist(Long id, String name, Lable lable) {
		super();
		this.id = id;
		this.name = name;
		this.lable = lable;
	}	
	
	public Artist() {
		super();
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", agencyCompany=" + lable + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}