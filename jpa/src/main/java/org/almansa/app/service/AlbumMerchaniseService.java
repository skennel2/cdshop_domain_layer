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
import org.almansa.app.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public final class AlbumMerchaniseService extends MerchandiseService {

    private AlbumRepository albumRepo;
    private AlbumMerchandiseRepository albumMerchandiseRepo;

    @Autowired
    public AlbumMerchaniseService(MerchandiseRepository merchanRepo, AlbumRepository albumRepo,
            AlbumMerchandiseRepository albumMerchandiseRepo) {
        super(merchanRepo);
        this.albumRepo = albumRepo;
        this.albumMerchandiseRepo = albumMerchandiseRepo;
    }

    public void addAlbumMerchandise(Long albumId, Money price, int remainingStock) throws EntityNotFoundException  {            
        Album album = albumRepo.findById(albumId).orElseThrow(()-> new EntityNotFoundException());
        
        AlbumMerchandise newAlbumMerchandise = new AlbumMerchandise(new Long(remainingStock), price, album);
        albumMerchandiseRepo.save(newAlbumMerchandise);
    }

    public List<AlbumMerchandiseDetailViewModel> getByPageNumbers(int pageSize, int pageNumber) {
        PageRequest request = PageRequest.of(pageNumber, pageSize, new Sort(Direction.DESC, "id"));

        List<AlbumMerchandiseDetailViewModel> resultList = new ArrayList<>();
        for (AlbumMerchandise merchandise : albumMerchandiseRepo.findAll(request).getContent()) {
            resultList.add(new AlbumMerchandiseDetailViewModel(merchandise));
        }

        return resultList;
    }

    public AlbumMerchandiseDetailViewModel getDetailViewModelById(Long merchandiseId) {
        AlbumMerchandise merchandise = albumMerchandiseRepo.getOne(merchandiseId);

        return new AlbumMerchandiseDetailViewModel(merchandise);
    }
}
