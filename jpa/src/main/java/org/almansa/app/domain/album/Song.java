package org.almansa.app.domain.album;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.almansa.app.domain.NamedEntityBase;
import org.almansa.app.domain.PersonBase;

@Entity
@AttributeOverride(name = "name", column = @Column(name = "song_name"))
public class Song extends NamedEntityBase {

    @ManyToOne
    @JoinColumn(name = "owner_artist_id")
    private Artist ownerArtist;

    @ElementCollection
    @CollectionTable(name = "song_writers", joinColumns = @JoinColumn(name = "song_writer_id"))
    private List<PersonAsSongWriter> artists = new ArrayList<PersonAsSongWriter>();

    @Column(name = "lylics")
    @Lob
    private String lylics;

    public Song(String name, Artist ownerArtist, List<PersonAsSongWriter> artists, String lylics) {
        super(name);
        this.ownerArtist = ownerArtist;
        this.artists = artists;
        this.lylics = lylics;

        if (artists == null) {
            this.artists = new ArrayList<PersonAsSongWriter>();
        }
    }
    
    public List<PersonBase> getProcedures() {
        List<PersonBase> result = new ArrayList<PersonBase>();
        for (PersonAsSongWriter personAsSongWriter : artists) {
            if (personAsSongWriter.getRole().equals(ProducerRole.MainProducer)
                    || personAsSongWriter.getRole().equals(ProducerRole.Producer)) {
                result.add(personAsSongWriter.getPerson());
            }
        }

        return result;
    }

    public String getDisplayName() {
        String displayName = this.getName();

        for (PersonAsSongWriter person : artists) {
            if (person.getRole().equals(ProducerRole.Featuring)) {
                displayName += " ," + person.getPerson().getName(); //TODO string formatting
            }
        }

        return displayName;
    }

    public void addPersonAsSongWriter(PersonAsSongWriter personAsSongWriter) {
        this.artists.add(personAsSongWriter);
    }
    
    public void addPersonAsSongWriter(PersonBase person, ProducerRole role) {
        this.artists.add(new PersonAsSongWriter(person, role));
    }

    public Artist getOwnerArtist() {
        return ownerArtist;
    }

    public void setOwnerArtist(Artist ownerArtist) {
        this.ownerArtist = ownerArtist;
    }

    public String getLylics() {
        return lylics;
    }

    @Override
    public String toString() {
        return "Song [ownerArtist=" + ownerArtist + ", artists=" + artists + ", lylics=" + lylics + "]";
    }

    /*
     * for jpa
     */
    protected Song() {
        super(null);
    }
}
