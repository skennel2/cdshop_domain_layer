package org.almansa.app.domain.album;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.almansa.app.domain.Builder;

public class AlbumBuilder implements Builder<Album> {

    private Artist albumArtist;
    private String albumName;
    private AlbumType albumType;
    private Date releaseDate;
    private List<SongInAlbum> songs = new ArrayList<SongInAlbum>();

    public AlbumBuilder addSong(Song song, int no, boolean isSingle) {
        return addSong(new SongInAlbum(song, no, isSingle));
    }

    public AlbumBuilder addSong(SongInAlbum song) {
        this.songs.add(song);
        return this;
    }

    public AlbumBuilder albumName(String name) {
        this.albumName = name;
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

    public AlbumBuilder name(String name) {
        this.albumName = name;
        return this;
    }

    public AlbumBuilder releaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public AlbumBuilder songs(List<SongInAlbum> songs) {
        this.songs = songs;
        return this;
    }

    public AlbumBuilder thisIsEPType() {
        albumType = AlbumType.EP;
        return this;
    }

    public AlbumBuilder thisIsLPType() {
        albumType = AlbumType.LP;
        return this;
    }
    
    @Override
    public Album Build() {
        Album album = new Album(albumName, albumArtist, releaseDate, songs, albumType);
        return album;
    }

	@Override
	public String toString() {
		return "AlbumBuilder [albumArtist=" + albumArtist + ", albumName=" + albumName + ", albumType=" + albumType
				+ ", releaseDate=" + releaseDate + ", songs=" + songs + ", thisIsEPType()=" + thisIsEPType()
				+ ", thisIsLPType()=" + thisIsLPType() + ", Build()=" + Build() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
}
