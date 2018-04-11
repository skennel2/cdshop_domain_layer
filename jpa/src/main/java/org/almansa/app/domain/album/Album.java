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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "album_artist_id")
    private Artist albumArtist;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ElementCollection
    @CollectionTable(name = "song_in_album", joinColumns = @JoinColumn(name = "song_in_album_id"))
    private List<SongInAlbum> songs = new ArrayList<SongInAlbum>();

    @ElementCollection
    @CollectionTable(name = "album_tag", joinColumns = @JoinColumn(name = "album_tag_id"))
    private List<CategoryTag> tags = new ArrayList<CategoryTag>();

    @Enumerated(EnumType.STRING)
    private AlbumType albumType;

    public Artist getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(Artist albumArtist) {
        this.albumArtist = albumArtist;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<SongInAlbum> getSongs() {
        return songs;
    }

    public void setSongs(List<SongInAlbum> songs) {
        this.songs = songs;
    }

    public AlbumType getAlbumType() {
        return albumType;
    }

    public void setAlbumType(AlbumType albumType) {
        this.albumType = albumType;
    }

    public List<CategoryTag> getTags() {
        return tags;
    }

    public void setTags(List<CategoryTag> tags) {
        this.tags = tags;
    }

    public void addCategory(CategoryTag tag) {
        tags.add(tag);
    }

    public void addSong(Song song, int no, boolean isSingle) {
        for (SongInAlbum songInAlbum : songs) {
            if (songInAlbum.getNo() == no) {
                throw new RuntimeException(); // TODO Temp Exception, duplicated no check, for statement looks so bad
            }
        }

        SongInAlbum songInAlbum = new SongInAlbum(this, song, no, isSingle);
        this.songs.add(songInAlbum);
    }
}