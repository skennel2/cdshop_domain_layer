package org.almansa.app.domain.album;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.almansa.app.domain.EntityBase;
import org.springframework.lang.NonNull;

/**
 * @author skennel
 *
 */
@Entity
public class Lable extends EntityBase {

    @NonNull
    @Column(name = "lable_name")
    private String name;

    @Column(name = "ceo_name")
    private String ceoName;

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    @Override
    public String toString() {
        return "Lable [name=" + name + ", ceoName=" + ceoName + "]";
    }
}