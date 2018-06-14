package org.almansa.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.repository.ArtistRepository;
import org.almansa.app.repository.LableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepo;

    @Autowired
    private LableRepository lableRepo;

    public void add(Long lableId, String name, Date bornDate) throws IllegalArgumentException{
        if(name == null) {
            throw new IllegalArgumentException("name can't be null");
        }
        Lable lable = lableRepo.findById(lableId).orElse(null);

        Artist artist = new Artist(name, bornDate, lable);
        artistRepo.save(artist);
    }

    public Artist findById(Long artistId) {
        return artistRepo.findById(artistId).orElse(null);
    }

    public List<Artist> getAll() {
        return artistRepo.findAll();
    }
}
