package org.almansa.app.domain.album;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.almansa.app.domain.EntityBase;
import org.almansa.app.domain.PersonBase;
import org.springframework.lang.NonNull;

@Entity
public class Song extends EntityBase {

    @NonNull
    @Column(name = "song_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_artist_id")
    private Artist ownerArtist;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable
    private List<PersonBase> artists;

    @ManyToOne
    @JoinColumn(name = "main_producer_id")
    private Producer mainProducer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return super.toString() + " Song [name=" + name + ", ownerArtist=" + ownerArtist + ", artists=" + artists
                + ", producer=" + mainProducer + "]";
    }
}