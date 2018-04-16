package org.almansa.app.domain.album;

import javax.persistence.Embeddable;

import org.almansa.app.domain.NamedEntityBase;

@Embeddable 
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