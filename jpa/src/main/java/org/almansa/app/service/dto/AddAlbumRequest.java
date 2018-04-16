package org.almansa.app.service.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.domain.dto.SongIdAndSongNo;

public class AddAlbumRequest {

    private Long artistId;

    private String albumName;

    private Date releaseDate;

    private List<SongIdAndSongNo> songIds = new ArrayList<SongIdAndSongNo>();

    private List<String> tag = new ArrayList<String>();

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

    public List<SongIdAndSongNo> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<SongIdAndSongNo> songIds) {
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