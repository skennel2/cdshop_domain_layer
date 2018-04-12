package org.almansa.app.domain.album;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.almansa.app.domain.NamedEntityBase;
import org.almansa.app.domain.PersonBase;

@Entity
@AttributeOverride(name = "name", column = @Column(name = "song_name"))
public class Song extends NamedEntityBase {

    @ManyToOne
    @JoinColumn(name = "owner_artist_id")
    private Artist ownerArtist;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable
    private List<PersonBase> artists;

    @ManyToOne
    @JoinColumn(name = "main_producer_id")
    private Producer mainProducer;
    
    @Column(name="lylics")
    private String lylics;

    public Artist getOwnerArtist() {
        return ownerArtist;
    }

    public void setOwnerArtist(Artist ownerArtist) {
        this.ownerArtist = ownerArtist;
    }

    public Producer getMainProducer() {
        return mainProducer;
    }

    public void setMainProducer(Producer producer) {
        this.mainProducer = producer;
    }

    public List<PersonBase> getArtists() {
        return artists;
    }

    public void setArtists(List<PersonBase> artists) {
        this.artists = artists;
    }

    public String getLylics() {
        return lylics;
    }

    @Override
    public String toString() {
        return "Song [ownerArtist=" + ownerArtist + ", artists=" + artists + ", mainProducer=" + mainProducer
                + ", lylics=" + lylics + "]";
    }   
}