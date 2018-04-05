package org.almansa.app.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="Producer")
public class Producer extends PersonBase{
	
	@ManyToOne()
	@JoinColumn(name="agency_company_id")
	private Lable lable;
	
	public Lable getLable() {
		return lable;
	}

	public void setLable(Lable lable) {
		this.lable = lable;
	}

	@Override
	public String toString() {
		return "Producer [lable=" + lable + "]";
	}		
}