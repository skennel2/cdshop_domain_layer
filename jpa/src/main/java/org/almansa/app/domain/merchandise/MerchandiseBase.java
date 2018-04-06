package org.almansa.app.domain.merchandise;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.value.Money;

@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn
@Entity
@Table(name="Merchandise")
public abstract class MerchandiseBase extends EntityBase{
	
	@Column(name="amount_of_stock")
	private Long amountOfStock = Long.valueOf(0);
	
	@Embedded
	@AttributeOverride(name="amount", column=@Column(name="price"))
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