package org.almansa.app.service;

import java.util.Optional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.value.Money;
import org.almansa.app.repository.AlbumMerchandiseRepository;
import org.almansa.app.repository.AlbumRepository;

public class AlbumMerchaniseService {

    private AlbumRepository albumRepo;
    private AlbumMerchandiseRepository merchanRepo;

    public void addAlbumMerchandise(Long albumId, Money price, int remainingStock) {
        Optional<Album> album = albumRepo.findById(albumId);

        AlbumMerchandise newAlbumMerchan = new AlbumMerchandise();
        newAlbumMerchan.addStock(remainingStock);
        newAlbumMerchan.setAlbum(album.get());
        newAlbumMerchan.setAmountOfStock(new Long(remainingStock));
        newAlbumMerchan.setPrice(price);
    }
}
