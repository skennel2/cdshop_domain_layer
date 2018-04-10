package org.almansa.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.CategoryTag;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.album.SongInAlbum;
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
    public void AddAlbum(AlbumAddModel addModel) {
        Optional<Artist> artist = artistRepo.findById(addModel.getArtistId());

        if (!artist.isPresent()) {
            throw new RuntimeException();
        }

        Album newAlbum = new Album();
        if (addModel.getSongIds() != null) {
            int no = 1;
            for (Long songId : addModel.getSongIds()) {
                Optional<Song> song = songRepo.findById(songId);
                if (song.isPresent()) {
                    SongInAlbum songInModel = new SongInAlbum();
                    songInModel.setSingle(false);
                    songInModel.setSong(song.get());
                    songInModel.setNo(no++);
                    songInModel.setAlbum(newAlbum);
                }
            }
        }

        List<CategoryTag> categoryTags = new ArrayList<CategoryTag>();
        if (addModel.getTag() != null) {
            for (String tag : addModel.getTag()) {
                CategoryTag categoryTag = new CategoryTag();
                categoryTag.setName(tag);
                categoryTag.setCreationDate(new Date());
                categoryTags.add(categoryTag);
            }
        }

        newAlbum.setTags(categoryTags);
        newAlbum.setAlbumArtist(artist.get());
        newAlbum.setName(addModel.getAlbumName());
        newAlbum.setReleaseDate(addModel.getReleaseDate());
        newAlbum.setAlbumType(addModel.getAlbumType());

        albumRepo.save(newAlbum);
    }
}
