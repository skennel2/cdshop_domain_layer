package org.almansa.app.domain.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.almansa.app.domain.merchandise.MerchandiseBase;
import org.almansa.app.domain.value.Money;

@Embeddable
@Table(name = "PUCHASE_ORDER_LINE")
public class PurchaseOrderLine {

    @ManyToOne
    @JoinColumn(name = "merchandise_id")
    private MerchandiseBase merchandise;

    @Column(name = "product_quantity")
    private int quantity;

    public MerchandiseBase getMerchandise() {
        return merchandise;
    }

    public Money calculateTotalPrice() {
        Long amount = merchandise.getPrice().getAmount().longValue() * quantity;

        return new Money(amount);
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

    @Override
    public String toString() {
        return "PurchaseOrderLine [merchandise=" + merchandise + ", quantity=" + quantity + "]";
    }
}