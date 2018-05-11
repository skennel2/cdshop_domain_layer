package org.almansa.app.domain.album;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.almansa.app.domain.NamedEntityBase;
import org.almansa.app.domain.PersonBase;

@Entity
@AttributeOverride(name = "name", column = @Column(name = "song_name"))
public class Song extends NamedEntityBase {

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "song_writers", joinColumns = @JoinColumn(name = "song_writer_id"))
    private List<PersonAsSongWriter> artists = new ArrayList<PersonAsSongWriter>();

    @Column(name = "lylics")
    @Lob
    private String lylics;

    @ManyToOne
    @JoinColumn(name = "owner_artist_id")
    private Artist ownerArtist;

    protected Song() {
        super(null);
    }
 
    public Song(String name, Artist ownerArtist, List<PersonAsSongWriter> artists, String lylics) {
        super(name);
        this.ownerArtist = ownerArtist;
        this.artists = artists;
        this.lylics = lylics;

        if (artists == null) {
            this.artists = new ArrayList<PersonAsSongWriter>();
        }
    }

    public Song(String name, Artist ownerArtist, String lylics) {
        super(name);
        this.ownerArtist = ownerArtist;
        this.lylics = lylics;
        this.artists = new ArrayList<PersonAsSongWriter>();
    }

    public void setMainProducer(PersonBase person) {
        this.artists.add(new PersonAsSongWriter(person, ProducerRole.MainProducer));
    }
    
    public void addPersonAsSongWriter(PersonAsSongWriter personAsSongWriter) {
        this.artists.add(personAsSongWriter);
    }

    public void addPersonAsSongWriter(PersonBase person, ProducerRole role) {
        this.artists.add(new PersonAsSongWriter(person, role));
    }

    public String getLylics() {
        return lylics;
    }

    public Artist getOwnerArtist() {
        return ownerArtist;
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

    public void changeOwnerArtist(Artist ownerArtist) {
        this.ownerArtist = ownerArtist;
    }
    
    @Override
    public String toString() {
        return "Song [ownerArtist=" + ownerArtist + ", artists=" + artists + ", lylics=" + lylics + "]";
    }
}
