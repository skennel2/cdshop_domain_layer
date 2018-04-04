package org.almansa.app.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CategoryTag implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="category_name")
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	public CategoryTag() {
		super();
	}

	public CategoryTag(String name) {
		super();
		this.name = name;
		this.creationDate = new Date();
	}
	
	public CategoryTag(Long id, String name, Date creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
	}	
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", creationDate=" + creationDate + "]";
	}	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
		CategoryTag other = (CategoryTag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
