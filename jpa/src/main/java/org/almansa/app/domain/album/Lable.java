package org.almansa.app.domain.album;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.almansa.app.domain.NamedEntityBase;

/**
 * @author skennel
 *
 */
@Entity
@AttributeOverride(name = "name", column = @Column(name = "lable_name"))
public class Lable extends NamedEntityBase {

    @Column(name = "ceo_name")
    private String ceoName;    
    
    @Column(name = "est_date")
    private Date establishDate;
    
    public Lable(String name) {
        super(name);
    }    
    
    public Lable(String name, String ceoName) {
        super(name);
        this.ceoName = ceoName;
    }
    
    public Lable(String name, String ceoName, Date establishDate) {
        super(name);
        this.ceoName = ceoName;
        this.establishDate = establishDate;
    }
    
    protected Lable() {
        
    }

    public String getCeoName() {
        return ceoName;
    }

    public void changeCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void changeEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    @Override
    public String toString() {
        return "Lable [ceoName=" + ceoName + ", establishDate=" + establishDate + "]";
    }
}