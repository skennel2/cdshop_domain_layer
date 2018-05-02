package org.almansa.app.domain.value;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.almansa.app.domain.Immutable;

@Embeddable
public class Money implements Immutable {

    @Column(name = "amount_of_money")
    private BigDecimal amount;

    protected Money() {
        super();
    }

    public Money(BigDecimal amount) {
        super();
        this.amount = amount;
    }

    public Money(long amount) {
        super();
        this.amount = new BigDecimal(amount);
    }

    public Money(String amount) {
        super();
        this.amount = new BigDecimal(amount);
    }

    public Money add(Money add) {
        return new Money(this.amount.add(add.amount));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Money other = (Money) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        return true;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        return result;
    }

}