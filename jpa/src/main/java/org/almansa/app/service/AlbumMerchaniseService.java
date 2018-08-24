package org.almansa.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.dto.AlbumMerchandiseDetailViewModel;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
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
public class AlbumMerchaniseService extends MerchandiseService {

    private AlbumRepository albumRepo;
    private AlbumMerchandiseRepository albumMerchandiseRepo;

    @Autowired
    public AlbumMerchaniseService(MerchandiseRepository merchanRepo, AlbumRepository albumRepo,
            AlbumMerchandiseRepository albumMerchandiseRepo) {
        super(merchanRepo);
        this.albumRepo = albumRepo;
        this.albumMerchandiseRepo = albumMerchandiseRepo;
    }

    public void add(Long albumId, Money price, int remainingStock) throws EntityNotFoundException  {            
        Album album = albumRepo.findById(albumId).orElseThrow(()-> new EntityNotFoundException());
        
        AlbumMerchandise newAlbumMerchandise = new AlbumMerchandise(new Long(remainingStock), price, album);
        albumMerchandiseRepo.save(newAlbumMerchandise);
    }

    public List<AlbumMerchandiseDetailViewModel> findByPageNumbers(int pageSize, int pageNumber) {
        PageRequest request = PageRequest.of(pageNumber, pageSize, new Sort(Direction.DESC, "id"));

        List<AlbumMerchandiseDetailViewModel> resultList = albumMerchandiseRepo
                .findAll(request)
                .stream()
                .map((m)->{
                    return new AlbumMerchandiseDetailViewModel(m);
                 })
                .collect(Collectors.toList());

        return resultList;
    }

    public AlbumMerchandiseDetailViewModel findDetailViewModelById(Long merchandiseId) {
        AlbumMerchandise merchandise = albumMerchandiseRepo.getOne(merchandiseId);

        return new AlbumMerchandiseDetailViewModel(merchandise);
    }
}
