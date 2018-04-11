package org.almansa.app.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.CategoryTag;
import org.almansa.app.repository.AlbumRepository;
import org.almansa.app.repository.ArtistRepository;
import org.almansa.app.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    private AlbumRepository albumRepo;
    private ArtistRepository artistRepo;
    private SongRepository songRepo;

    @Autowired
    public AlbumService(AlbumRepository albumRepo, ArtistRepository artistRepo, SongRepository songRepo) {
        super();
        this.albumRepo = albumRepo;
        this.artistRepo = artistRepo;
        this.songRepo = songRepo;
    }

    @Transactional
    public void AddAlbum(AlbumAddParameterModel addModel) {
        Optional<Artist> artist = this.artistRepo.findById(addModel.getArtistId());

        if (!artist.isPresent()) {
            throw new RuntimeException(); //TODO looks bad
        }

        Album newAlbum = new Album();
        
        CategoryTag categoryTag = null;
        if (addModel.getTag() != null) {
            for (String tag : addModel.getTag()) {
                categoryTag = new CategoryTag();
                categoryTag.setName(tag);
                categoryTag.setCreationDate(new Date());
                newAlbum.addCategory(categoryTag);
            }
        }

        newAlbum.setAlbumArtist(artist.get());
        newAlbum.setName(addModel.getAlbumName());
        newAlbum.setReleaseDate(addModel.getReleaseDate());
        newAlbum.setAlbumType(addModel.getAlbumType());

        this.albumRepo.save(newAlbum);
    }
}
