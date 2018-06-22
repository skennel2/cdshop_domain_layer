package org.almansa.app.domain.album;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.PersonBase;

@Entity
public class PersonAsSongWriter extends EntityBase {

    @ManyToOne
    private PersonBase person;

    @Enumerated(EnumType.STRING)
    private ProducerRole role;

    public PersonAsSongWriter(PersonBase person, ProducerRole role) {
        super();
        this.person = person;
        this.role = role;
    }
    
    protected PersonAsSongWriter() {
    }

    public PersonBase getPerson() {
        return person;
    }

    public ProducerRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "PersonAsSongWriter [person=" + person + ", role=" + role + "]";
    }

}