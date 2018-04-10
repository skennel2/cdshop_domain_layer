package org.almansa.app.service;

import java.util.Date;

import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.repository.AlbumRepository;
import org.almansa.app.repository.ArtistRepository;
import org.almansa.app.repository.LableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    
    private AlbumRepository albumRepo;
    private ArtistRepository artistRepo;
    private LableRepository lableRepo;

    @Autowired
    public AlbumService(AlbumRepository albumRepo, ArtistRepository artistRepo, LableRepository lableRepo) {
        super();
        this.albumRepo = albumRepo;
        this.artistRepo = artistRepo;
        this.lableRepo = lableRepo;
    }

    public void AddAlbum(Long aritstId, Long lableId, String albumName, Date releaseDate, AlbumType albumType) {
        
    }
}
