package org.almansa.app.domain.dto;

import java.util.Date;

import org.almansa.app.domain.album.AlbumType;

public class AlbumSimpleViewModel {

    private Long albumId;
    private String albumName;
    private AlbumType albumType;
    private String artistName;
    private int containingSongCount;
    private Date releaseDate;

    public Long getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public AlbumType getAlbumType() {
        return albumType;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getContainingSongCount() {
        return containingSongCount;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumType(AlbumType albumType) {
        this.albumType = albumType;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setContainingSongCount(int containingSongCount) {
        this.containingSongCount = containingSongCount;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "AlbumSimpleViewModel [albumId=" + albumId + ", albumName=" + albumName + ", artistName=" + artistName
                + ", releaseDate=" + releaseDate + ", albumType=" + albumType + ", containingSongCount="
                + containingSongCount + "]";
    }
}
