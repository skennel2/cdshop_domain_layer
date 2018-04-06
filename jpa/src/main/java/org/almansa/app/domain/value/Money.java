package org.almansa.app.domain.value;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Money {
	
	@Column(name="amount_of_money")
	private BigDecimal amount;

	public Money(BigDecimal amount) {
		super();
		this.amount = amount;
	}
	
	public Money(String amount) {
		super();
		this.amount = new BigDecimal(amount);
	}	
	
	protected Money() {
		super();	
	}

	public BigDecimal getAmount() {
		return amount;
	}
}
