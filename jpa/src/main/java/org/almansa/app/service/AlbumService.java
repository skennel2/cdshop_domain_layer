package org.almansa.app.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.CategoryTag;
import org.almansa.app.domain.album.Song;
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
    public void AddAlbum(AlbumAddParameterModel addParameter) {
        Optional<Artist> artist = this.artistRepo.findById(addParameter.getArtistId());

        Album newAlbum = new Album();                
                
        for (SongIdAndSongNo songId : addParameter.getSongIds()) {                
            Optional<Song> albumSong = this.songRepo.findById(songId.getSongId());
            
            if(albumSong.isPresent()) {
                newAlbum.addSong(albumSong.get(), songId.getNo(), false);
            }
        }
        
        for (String tag : addParameter.getTag()) {
            CategoryTag categoryTag = new CategoryTag();
            categoryTag.setName(tag);
            categoryTag.setCreationDate(new Date());
            newAlbum.addCategory(categoryTag);
        }

        newAlbum.setAlbumArtist(artist.get());
        newAlbum.setName(addParameter.getAlbumName());
        newAlbum.setReleaseDate(addParameter.getReleaseDate());
        newAlbum.setAlbumType(addParameter.getAlbumType());

        this.albumRepo.save(newAlbum);
    }
    
    @Transactional
    public void changeAlbumName(Long albumId, String newName) {
        Album album = albumRepo.findOne(albumId);                
        
        if(album != null) {
            album.setName(newName);
        }
    }
}
