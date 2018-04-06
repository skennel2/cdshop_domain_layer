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
    @Column(name = "agency_company_name")
    private String name;

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Lable [name=" + name + "]";
    }
}