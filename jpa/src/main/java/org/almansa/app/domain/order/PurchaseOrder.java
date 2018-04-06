package org.almansa.app.domain.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.user.User;
import org.almansa.app.domain.value.Money;

@Entity
public class PurchaseOrder extends EntityBase {

	@ManyToOne
	@JoinColumn(name = "oderer_user_id")
	private User orderer;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	@ElementCollection
	@CollectionTable(
		name="order_line",
		joinColumns=@JoinColumn(name="order_id")
	)
	private List<PurchaseOrderLine> OrderLines;

	public Money calculateTotalPrice() {
		Money money = new Money(0);
		
		for (PurchaseOrderLine orderLine : OrderLines) {
			money = money.add(orderLine.calculateTotalPrice());
		}
		
		return money;
	}
	
	public User getOrderer() {
		return orderer;
	}

	public void setOrderer(User orderer) {
		this.orderer = orderer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<PurchaseOrderLine> getOrderLines() {
		return OrderLines;
	}

	public void setOrderLines(List<PurchaseOrderLine> orderLines) {
		OrderLines = orderLines;
	}	
}