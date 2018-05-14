package org.almansa.app.domain.dto;

import java.util.Date;

import org.almansa.app.domain.Immutable;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.value.Money;

public class AlbumMerchandiseDetailViewModel implements Immutable {

    private String albumArtistName;
    private String albumName;
    private Long merchandiseId;
    private Money price;
    private Date releaseDate;

    public AlbumMerchandiseDetailViewModel(AlbumMerchandise merchandise) {
        super();

        this.merchandiseId = merchandise.getId();
        this.albumName = merchandise.getAlbum().getName();
        this.albumArtistName = merchandise.getAlbum().getAlbumArtist().getName();
        this.price = merchandise.getPrice();
        this.releaseDate = merchandise.getAlbum().getReleaseDate();
    }

    public String getAlbumArtistName() {
        return albumArtistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public Long getMerchandiseId() {
        return merchandiseId;
    }

    public Money getPrice() {
        return price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "AlbumMerchandiseDetail [merchandiseId=" + merchandiseId + ", albumName=" + albumName
                + ", albumArtistName=" + albumArtistName + ", price=" + price + ", releaseDate=" + releaseDate + "]";
    }
}