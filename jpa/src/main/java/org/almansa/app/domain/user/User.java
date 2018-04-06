package org.almansa.app.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.almansa.app.domain.EntityBase;

@Entity
public class User extends EntityBase {
    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
