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

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AlbumType getAlbumType() {
        return albumType;
    }

    public void setAlbumType(AlbumType albumType) {
        this.albumType = albumType;
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
