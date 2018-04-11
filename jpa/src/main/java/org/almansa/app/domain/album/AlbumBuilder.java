package org.almansa.app.domain.album;

import java.util.Date;

public class AlbumBuilder {

    private String albumName;
    
    private AlbumType albumType;
    
    private Artist albumArtist;
    
    private Date releaseDate;
    
    public AlbumBuilder albumName(String name) {
        this.albumName= name;
        return this;
    }
    
    public AlbumBuilder thisIsLPType() {
        albumType = AlbumType.LP;
        return this;
    }
    
    public AlbumBuilder thisIsEPType() {
        albumType = AlbumType.EP;
        return this;
    }
    
    public AlbumBuilder albumType(AlbumType albumType) {
        this.albumType = albumType;
        return this;
    }   
    
    public AlbumBuilder artist(Artist artist) {
        this.albumArtist = artist;
        return this;
    }   
    
    public AlbumBuilder releaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
    
    public Album Build() {
        Album album = new Album();
        album.setAlbumArtist(albumArtist);
        
        return album;
    }
}
