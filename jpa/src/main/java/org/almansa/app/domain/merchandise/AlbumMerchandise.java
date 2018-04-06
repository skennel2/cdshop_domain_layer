package org.almansa.app.domain.merchandise;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.almansa.app.domain.Album;

@Entity
@Table(name="ALBUM_MERCHANDISE")
public class AlbumMerchandise extends MerchandiseBase{
	
	@OneToOne
	private Album album;

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}		
}