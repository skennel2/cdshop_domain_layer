package org.almansa.app.domain.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.almansa.app.domain.merchandise.MerchandiseBase;

@Embeddable
public class OrderLine{
	
	@ManyToOne
	@JoinColumn(name="merchandise_id")
	private MerchandiseBase merchandise; 
	
	@Column(name="product_quantity")
	private int quantity;

	public MerchandiseBase getMerchandise() {
		return merchandise;
	}

	public void setMerchandise(MerchandiseBase merchandise) {
		this.merchandise = merchandise;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}