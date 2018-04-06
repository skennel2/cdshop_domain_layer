package org.almansa.app.domain.album;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.almansa.app.domain.PersonBase;

@Entity
@DiscriminatorValue(value = "Producer")
public class Producer extends PersonBase {

    @ManyToOne()
    @JoinColumn(name = "agency_company_id")
    private Lable lable;

    @Enumerated(EnumType.STRING)
    public ProducerRole role;
    
    public Lable getLable() {
        return lable;
    }

    public void setLable(Lable lable) {
        this.lable = lable;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Producer [lable=" + lable + "]";
    }
}