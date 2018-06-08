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
import org.almansa.app.domain.exception.RemainingStockException;
import org.almansa.app.domain.value.Money;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Entity
@Table(name = "Merchandise")
public abstract class MerchandiseBase extends EntityBase {

    @Column(name = "amount_of_stock")
    private Long amountOfStock = Long.valueOf(0);

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    protected MerchandiseBase() {
        super();
    }

    public MerchandiseBase(Long amountOfStock, Money price) throws IllegalArgumentException {
        super();
        if (amountOfStock == null || amountOfStock < 0) {
            throw new IllegalArgumentException("amount argument is smaller then zero");
        }

        if (price == null || price.getAmount().longValue() < 0) {
            throw new IllegalArgumentException("price is smaller then zero");
        }

        this.amountOfStock = amountOfStock;
        this.price = price;
    }

    public Long getAmountOfStock() {
        return amountOfStock;
    }

    public Money getPrice() {
        return price;
    }

    public boolean isAbailableOrderQuantity(long amount) {
        return ((amountOfStock - amount) >= 0);
    }

    public boolean isSoldOut() {
        return amountOfStock.longValue() == 0;
    }

    public void addStock(long amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("amount argument is smaller then zero");
        }

        this.amountOfStock += amount;
    }

    public void removeStock(long amount) throws RemainingStockException, IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("amount argument is smaller then zero");
        }

        if ((amountOfStock - amount) < 0) {
            throw new RemainingStockException("out of stock");
        }

        this.amountOfStock -= amount;
    }

    public void changeAmountOfStock(Long amountOfStock) throws IllegalArgumentException {
        if (amountOfStock == null || amountOfStock < 0) {
            throw new IllegalArgumentException("amountOfStock argument not allowd null and Zero");
        }

        this.amountOfStock = amountOfStock;
    }

    public void changePrice(Money price) throws IllegalArgumentException {
        if (price == null || price.getAmount().longValue() < 0) {
            throw new IllegalArgumentException("price argument not allowd null and Zero");
        }

        this.price = price;
    }

    @Override
    public String toString() {
        return "MerchandiseBase [amountOfStock=" + amountOfStock + ", price=" + price + "]";
    }

}