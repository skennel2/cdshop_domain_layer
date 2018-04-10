package org.almansa.app.service;

import java.util.Date;
import java.util.List;

import org.almansa.app.domain.album.AlbumType;

public class AlbumAddModel {
    private Long artistId;
    private String albumName;
    private Date releaseDate;
    private List<Long> songIds;
    private List<String> tag;
    private AlbumType albumType;

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Long> getSongId() {
        return songIds;
    }

    public void setSongId(List<Long> songIds) {
        this.songIds = songIds;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public AlbumType getAlbumType() {
        return albumType;
    }

    public void setAlbumType(AlbumType albumType) {
        this.albumType = albumType;
    }
}