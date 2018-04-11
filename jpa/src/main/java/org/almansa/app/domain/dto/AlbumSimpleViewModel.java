package org.almansa.app.domain.dto;

import java.util.Date;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumType;

public class AlbumSimpleViewModel {

    private Long albumId;

    private String albumName;

    private String artistName;

    private Date releaseDate;

    private AlbumType albumType;

    private int containingSongCount;

    public AlbumSimpleViewModel(Album album) {
        if (album.isNew()) {
            throw new RuntimeException(); // TODO
        }
        this.albumId = album.getId();
        this.albumName = album.getName();
        this.artistName = album.getAlbumArtist().getName();
        this.releaseDate = album.getReleaseDate();
        this.albumType = album.getAlbumType();
        this.containingSongCount = album.getSongs().size();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public AlbumType getAlbumType() {
        return albumType;
    }

    public int getContainingSongCount() {
        return containingSongCount;
    }

    public void setContainingSongCount(int containingSongCount) {
        this.containingSongCount = containingSongCount;
    }

    @Override
    public String toString() {
        return "AlbumSimpleViewModel [albumId=" + albumId + ", albumName=" + albumName + ", artistName=" + artistName
                + ", releaseDate=" + releaseDate + ", albumType=" + albumType + ", containingSongCount="
                + containingSongCount + "]";
    }
}
