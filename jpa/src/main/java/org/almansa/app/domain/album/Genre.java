package org.almansa.app.domain.album;

import javax.persistence.Entity;

import org.almansa.app.domain.NamedEntityBase;

@Entity
public class Genre extends NamedEntityBase {

    /*
     * for jpa
     */
    protected Genre() {
        super(null);
    }

    public Genre(String name) {
        super(name);
    }
}