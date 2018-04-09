package org.almansa.app.domain.album;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.almansa.app.domain.EntityBase;

@Entity
@Table(name = "IMAGE")
public class Image extends EntityBase {

	@Column
	private String accessPath;

	@Column
	private String description;

	public String getAccessPath() {
		return accessPath;
	}

	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
