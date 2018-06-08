package org.almansa.app.domain.album;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.almansa.app.domain.PersonBase;

@Entity
@DiscriminatorValue(value = "Artist")
@AttributeOverride(name = "name", column = @Column(name = "artist_name"))
public class Artist extends PersonBase {

    @ManyToOne
    @JoinColumn(name = "agency_company_id")
    private Lable lable;

    public Artist(String name, Date bornDate, Lable lable) {
        super(name, bornDate);
        this.lable = lable;
    }
    
    protected Artist() {
    }

    public void changeLable(Lable lable) {
        this.lable = lable;
    }

    public Lable getLable() {
        return lable;
    }

    @Override
    public String toString() {
        return "Artist [lable=" + lable + "]";
    }
}