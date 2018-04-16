package org.almansa.app.domain.album;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.almansa.app.domain.Builder;

public class AlbumBuilder implements Builder<Album> {

    private String albumName;

    private AlbumType albumType;

    private Artist albumArtist;

    private Date releaseDate;

    private List<SongInAlbum> songs = new ArrayList<SongInAlbum>();

    private List<CategoryTag> tags = new ArrayList<CategoryTag>();

    public AlbumBuilder addSong(SongInAlbum song) {
        this.songs.add(song);
        return this;
    }

    public AlbumBuilder addSong(Song song, int no, boolean isSingle) {
        return addSong(new SongInAlbum(song, no, isSingle));
    }

    public AlbumBuilder addTag(CategoryTag tag) {
        this.tags.add(tag);
        return this;
    }

    public AlbumBuilder songs(List<SongInAlbum> songs) {
        this.songs = songs;
        return this;
    }

    public AlbumBuilder tags(List<CategoryTag> tags) {
        this.tags = tags;
        return this;
    }

    public AlbumBuilder albumName(String name) {
        this.albumName = name;
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

    public AlbumBuilder name(String name) {
        this.albumName = name;
        return this;
    }

    @Override
    public Album Build() {
        Album album = new Album(albumName, albumArtist, releaseDate, songs, tags, albumType);
        return album;
    }
}
