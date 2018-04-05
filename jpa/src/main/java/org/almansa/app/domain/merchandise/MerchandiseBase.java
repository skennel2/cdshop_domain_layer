package org.almansa.app.domain.merchandise;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.value.Money;

public abstract class MerchandiseBase extends EntityBase{
	private Long amountOfStock;
	private Money price;
	
	public Long getAmountOfStock() {
		return amountOfStock;
	}
	
	public void setAmountOfStock(Long amountOfStock) {
		this.amountOfStock = amountOfStock;
	}
	
	public Money getPrice() {
		return price;
	}
	
	public void setPrice(Money price) {
		this.price = price;
	}
	
	public void addStock(long amount) {
		this.amountOfStock += amount;
	}
	
	public boolean isSoldOut() {
		return amountOfStock.longValue() == 0;
	}
}
