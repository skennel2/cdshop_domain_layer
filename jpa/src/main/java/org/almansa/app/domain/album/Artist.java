package org.almansa.app.domain.album;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.almansa.app.domain.PersonBase;

@Entity
@DiscriminatorValue(value = "Artist")
public class Artist extends PersonBase {

    @ManyToOne
    @JoinColumn(name = "agency_company_id")
    private Lable lable;

    public Lable getLable() {
        return lable;
    }

    public void changeLable(Lable lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "Artist [lable=" + lable + "]";
    }
}