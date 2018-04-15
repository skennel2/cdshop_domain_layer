package org.almansa.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.merchandise.MerchandiseBase;
import org.almansa.app.domain.value.Money;
import org.almansa.app.repository.AlbumMerchandiseRepository;
import org.almansa.app.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumMerchaniseService extends ServiceBase{

    private AlbumRepository albumRepo;
    private AlbumMerchandiseRepository merchanRepo;

    @Autowired
    public AlbumMerchaniseService(AlbumRepository albumRepo, AlbumMerchandiseRepository merchanRepo) {
        super();
        this.albumRepo = albumRepo;
        this.merchanRepo = merchanRepo;
    }

    @Transactional
    public void addAlbumMerchandise(Long albumId, Money price, int remainingStock) {
        Optional<Album> album = albumRepo.findById(albumId);

        AlbumMerchandise newAlbumMerchandise = new AlbumMerchandise(new Long(remainingStock), price, album.get());
        merchanRepo.save(newAlbumMerchandise);
    }
    
    @Transactional
    public void addStock(Long merchandiseId, Long amount) {
        MerchandiseBase merchandise = merchanRepo.getOne(merchandiseId);
        merchandise.addStock(amount);
    }
    
    @Transactional
    public void removeStock(Long merchandiseId, Long amount) {
        MerchandiseBase merchandise = merchanRepo.getOne(merchandiseId);
        if(merchandise.getAmountOfStock() - amount < 0) {
            throw new IllegalArgumentException("lack of stock");
        }
        merchandise.removeStock(amount);
    }
    
    @Transactional
    public void changeProductPrice(Long albumId, Money newPrice) { 
        AlbumMerchandise merchandise = merchanRepo.getOne(albumId);
        merchandise.setPrice(newPrice);
    }
}
