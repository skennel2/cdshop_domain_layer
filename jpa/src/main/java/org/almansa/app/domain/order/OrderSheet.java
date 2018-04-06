package org.almansa.app.domain.order;

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

@Entity
public class OrderSheet extends EntityBase {

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
	private List<OrderLine> OrderLines;

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

	public List<OrderLine> getOrderLines() {
		return OrderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		OrderLines = orderLines;
	}	
}