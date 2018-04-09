package org.almansa.app.domain.album;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.NamedEntityBase;
import org.springframework.lang.NonNull;

/**
 * @author skennel
 *
 */
@Entity
@AttributeOverride(name = "name", column = @Column(name = "lable_name"))
public class Lable extends NamedEntityBase {

	@Column(name = "ceo_name")
	private String ceoName;

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	@Override
	public String toString() {
		return "Lable [ceoName=" + ceoName + "]";
	}
}