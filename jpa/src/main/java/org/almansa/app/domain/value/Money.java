package org.almansa.app.domain.value;

import java.math.BigDecimal;

public class Money {
	
	private BigDecimal amount;

	public Money(BigDecimal amount) {
		super();
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}
}
