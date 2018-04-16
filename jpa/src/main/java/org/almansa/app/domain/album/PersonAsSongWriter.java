package org.almansa.app.domain.album;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.almansa.app.domain.PersonBase;

@Embeddable
public class PersonAsSongWriter {

    @ManyToOne
    private PersonBase person;

    @Enumerated(EnumType.STRING)
    private ProducerRole role;

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

    protected PersonAsSongWriter() {
        super();
    }
}