package org.almansa.app.domain.album;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

    @ElementCollection
    @CollectionTable(name = "song_writers", joinColumns = @JoinColumn(name = "song_writer_id"))
    private List<PersonAsSongWriter> artists = new ArrayList<PersonAsSongWriter>();

    @ManyToOne
    @JoinColumn(name = "main_producer_id")
    private Producer mainProducer;
    
    @Column(name="lylics")
    private String lylics;
    
    public String getDisplayName() {
        String displayName= this.getName();
        
        for (PersonAsSongWriter person : artists) {
            if(person.getRole().equals(ProducerRole.Featuring)) {
                displayName += " ," + person.getPerson().getName();
            }
        }
        
        return displayName;
    }

    public void addArtist(PersonBase artist, ProducerRole role) {       
        if(artist != null) {            
            artists.add(new PersonAsSongWriter(artist, role));
        }
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

    public String getLylics() {
        return lylics;
    }

    @Override
    public String toString() {
        return "Song [ownerArtist=" + ownerArtist + ", artists=" + artists + ", mainProducer=" + mainProducer
                + ", lylics=" + lylics + "]";
    }   
}
