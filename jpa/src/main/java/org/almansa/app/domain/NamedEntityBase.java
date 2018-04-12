package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NamedEntityBase extends EntityBase implements INamed {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public NamedEntityBase(String name) {
        super();
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void changeName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NamedEntitiyBase [name=" + name + "]";
    }
    
    /**
     * for jpa
     */
    protected NamedEntityBase() {
        super();
    }
}