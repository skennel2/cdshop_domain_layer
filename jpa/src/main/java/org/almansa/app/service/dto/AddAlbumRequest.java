package org.almansa.app.service.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.domain.dto.SongIdAndSongNo;

public class AddAlbumRequest {

    private String albumName;
    private AlbumType albumType;
    private Long artistId;
    private Date releaseDate;
    private List<SongIdAndSongNo> songIds = new ArrayList<SongIdAndSongNo>();

    public String getAlbumName() {
        return albumName;
    }

    public AlbumType getAlbumType() {
        return albumType;
    }

    public Long getArtistId() {
        return artistId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public List<SongIdAndSongNo> getSongIds() {
        return songIds;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumType(AlbumType albumType) {
        this.albumType = albumType;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setSongIds(List<SongIdAndSongNo> songIds) {
        this.songIds = songIds;
    }
}