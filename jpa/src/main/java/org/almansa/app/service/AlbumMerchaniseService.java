package org.almansa.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.dto.AlbumMerchandiseDetailViewModel;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.merchandise.MerchandiseBase;
import org.almansa.app.domain.value.Money;
import org.almansa.app.repository.AlbumMerchandiseRepository;
import org.almansa.app.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class AlbumMerchaniseService extends ServiceBase {

    private AlbumRepository albumRepo;
    private AlbumMerchandiseRepository merchanRepo;

    @Autowired
    public AlbumMerchaniseService(AlbumRepository albumRepo, AlbumMerchandiseRepository merchanRepo) {
        super();
        this.albumRepo = albumRepo;
        this.merchanRepo = merchanRepo;
    }

    public void addAlbumMerchandise(Long albumId, Money price, int remainingStock) throws EntityNotFoundException  {            
        Album album = albumRepo.findById(albumId).orElseThrow(()-> new EntityNotFoundException());
        
        AlbumMerchandise newAlbumMerchandise = new AlbumMerchandise(new Long(remainingStock), price, album);
        merchanRepo.save(newAlbumMerchandise);
    }

    public void addStock(Long merchandiseId, Long amount) throws EntityNotFoundException {
        MerchandiseBase merchandise = merchanRepo
                .findById(merchandiseId)
                .orElseThrow(()-> new EntityNotFoundException());
        
        merchandise.addStock(amount);
    }

    public void changeProductPrice(Long albumId, Money newPrice) throws EntityNotFoundException {
        MerchandiseBase merchandise = merchanRepo
                .findById(albumId)
                .orElseThrow(()-> new EntityNotFoundException());
        
        merchandise.changePrice(newPrice);
    }

    public List<AlbumMerchandiseDetailViewModel> getByPageNumbers(int pageSize, int pageNumber) {
        PageRequest request = PageRequest.of(pageNumber, pageSize, new Sort(Direction.DESC, "id"));

        List<AlbumMerchandiseDetailViewModel> resultList = new ArrayList<>();
        for (AlbumMerchandise merchandise : merchanRepo.findAll(request).getContent()) {
            resultList.add(new AlbumMerchandiseDetailViewModel(merchandise));
        }

        return resultList;
    }

    public AlbumMerchandiseDetailViewModel getDetailViewModelById(Long merchandiseId) {
        AlbumMerchandise merchandise = merchanRepo.getOne(merchandiseId);

        return new AlbumMerchandiseDetailViewModel(merchandise);
    }

    public void removeStock(Long merchandiseId, Long amount) throws EntityNotFoundException {
        MerchandiseBase merchandise = merchanRepo
                .findById(merchandiseId)
                .orElseThrow(()-> new EntityNotFoundException());
        
        merchandise.removeStock(amount);
    }
}
