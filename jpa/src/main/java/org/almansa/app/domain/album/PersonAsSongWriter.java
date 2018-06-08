package org.almansa.app.domain.album;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.PersonBase;

//@Embeddable
@Entity
public class PersonAsSongWriter extends EntityBase{

    @ManyToOne
    private PersonBase person;

    @Enumerated(EnumType.STRING)
    private ProducerRole role;

    protected PersonAsSongWriter() {
        super();
    }

    public PersonAsSongWriter(PersonBase person, ProducerRole role) {
        super();
        this.person = person;
        this.role = role;
    }

    public PersonBase getPerson() {
        return person;
    }

    public ProducerRole getRole() {
        return role;
    }
}