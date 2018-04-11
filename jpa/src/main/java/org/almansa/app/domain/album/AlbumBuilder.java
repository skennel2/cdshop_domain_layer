package org.almansa.app.domain.album;

public class AlbumBuilder {
    private AlbumType albumType;
    private Artist albumArtist;
    
    public AlbumBuilder lpType() {
        albumType = AlbumType.LP;
        return this;
    }
    
    public AlbumBuilder epType() {
        albumType = AlbumType.EP;
        return this;
    }
    
    public AlbumBuilder setAlbumType(AlbumType albumType) {
        this.albumType = albumType;
        return this;
    }   
    
    public AlbumBuilder setArtist(Artist artist) {
        this.albumArtist = artist;
        return this;
    }   
    
    public Album Build() {
        Album album = new Album();
        album.setAlbumArtist(albumArtist);
        
        return album;
    }
}
