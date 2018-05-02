package org.almansa.app.domain.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.merchandise.MerchandiseBase;
import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.value.Money;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder extends EntityBase {

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "odered_user_id")
    private ApplicationUser orderer;

    @ElementCollection
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_id"))
    private List<PurchaseOrderLine> orderLines;

    protected PurchaseOrder() {
        super();
    }

    public PurchaseOrder(ApplicationUser orderer, List<PurchaseOrderLine> orderLines, Date orderDate) {
        super();
        this.orderer = orderer;
        this.orderDate = orderDate;
        this.orderLines = orderLines;

        if (orderLines == null) {
            this.orderLines = new ArrayList<PurchaseOrderLine>();
        }
    }

    public void addOrderLine(MerchandiseBase merchandise, int quantity) {
        PurchaseOrderLine orderLine = new PurchaseOrderLine(merchandise, quantity);
        addOrderLine(orderLine);
    }

    public void addOrderLine(PurchaseOrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public Money calculateTotalPrice() {
        Money money = new Money(0);

        for (PurchaseOrderLine orderLine : orderLines) {
            money = money.add(orderLine.calculateTotalPrice());
        }

        return money;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public ApplicationUser getOrderer() {
        return orderer;
    }

    public List<PurchaseOrderLine> getOrderLines() {
        return orderLines;
    }

    @Override
    public String toString() {
        return "PurchaseOrder [orderer=" + orderer + ", orderDate=" + orderDate + ", OrderLines=" + orderLines + "]";
    }

}