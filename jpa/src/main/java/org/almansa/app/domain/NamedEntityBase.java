package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NamedEntityBase extends EntityBase implements INamed {
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NamedEntitiyBase [name=" + name + "]";
	}
}