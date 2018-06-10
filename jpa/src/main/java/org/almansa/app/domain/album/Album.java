package org.almansa.app.domain.album;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.almansa.app.domain.NamedEntityBase;

@Entity
@AttributeOverride(column = @Column(name = "album_name"), name = "name")
public class Album extends NamedEntityBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_artist_id")
    private Artist albumArtist;

    @Enumerated(EnumType.STRING)
    private AlbumType albumType;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "song_in_album", joinColumns = @JoinColumn(name = "song_in_album_id"))
    private List<SongInAlbum> songs;

    public Album(String name, Artist albumArtist, Date releaseDate, List<SongInAlbum> songs, AlbumType albumType) {
        super(name);

        this.albumArtist = albumArtist;
        this.releaseDate = releaseDate;
        this.songs = songs;
        this.albumType = albumType;

        if (songs == null) {
            this.songs = new ArrayList<SongInAlbum>();
        }
    }

    protected Album() {
    }
    
    public void addSong(Song song, int no, boolean isSingle) {
        SongInAlbum songInAlbum = new SongInAlbum(this, song, no, isSingle);
        addSong(songInAlbum);
    }

    public void addSong(SongInAlbum songInAlbum) {
        if (isExistsSongNumber(songInAlbum.getNo())) {
            throw new RuntimeException("Duplicated No"); // TODO Temp Exception,
        }

        songInAlbum.setAlbum(this);
        this.songs.add(songInAlbum);
    }

    public Artist getAlbumArtist() {
        return albumArtist;
    }

    public AlbumType getAlbumType() {
        return albumType;
    }

    public int getNumberOfContainingSongs() {
        return songs.size();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public List<SongInAlbum> getSongs() {
        return songs;
    }

    public boolean isExistsSongNumber(int no) {
        for (SongInAlbum songInAlbum : songs) {
            if (songInAlbum.getNo() == no) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Album [albumArtist=" + albumArtist + ", albumType=" + albumType + ", releaseDate=" + releaseDate
                + ", songs=" + songs + "]";
    }

}