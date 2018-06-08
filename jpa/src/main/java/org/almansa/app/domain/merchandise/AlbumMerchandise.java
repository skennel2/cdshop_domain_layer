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

    @OneToOne
    private Album album;

    protected AlbumMerchandise() {
        super();
    }

    public AlbumMerchandise(Long amountOfStock, Money price, Album album) {
        super(amountOfStock, price);
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

	@Override
	public String toString() {
		return "AlbumMerchandise [album=" + album + ", getAmountOfStock()=" + getAmountOfStock() + ", getPrice()="
				+ getPrice() + ", isSoldOut()=" + isSoldOut() + ", getId()=" + getId() + ", getCreationDate()="
				+ getCreationDate() + ", isNew()=" + isNew() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}        
}