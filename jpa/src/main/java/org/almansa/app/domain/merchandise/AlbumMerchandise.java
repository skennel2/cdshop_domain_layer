package org.almansa.app.domain.merchandise;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.value.Money;

@Entity
@Table(name = "ALBUM_MERCHANDISE")
@DiscriminatorValue(value = "Album")
public class AlbumMerchandise extends MerchandiseBase {

    public AlbumMerchandise(Long amountOfStock, Money price, Album album) {
        super(amountOfStock, price);
        this.album = album;
    }

    @OneToOne
    private Album album;

    public Album getAlbum() {
        return album;
    }
    
    /*
     * for jpa
     */
    protected AlbumMerchandise() {
        super();
    }
}