package org.almansa.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumBuilder;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.dto.AlbumSimpleViewModel;
import org.almansa.app.domain.dto.SongIdAndSongNo;
import org.almansa.app.repository.AlbumRepository;
import org.almansa.app.repository.ArtistRepository;
import org.almansa.app.repository.SongRepository;
import org.almansa.app.service.dto.AddAlbumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService extends ServiceBase {

    private AlbumAssembler albumAssembler;
    private AlbumRepository albumRepo;
    private ArtistRepository artistRepo;
    private SongRepository songRepo;

    @Autowired
    public AlbumService(AlbumRepository albumRepo, ArtistRepository artistRepo, SongRepository songRepo,
            AlbumAssembler albumAssembler) {
        super();
        this.albumRepo = albumRepo;
        this.artistRepo = artistRepo;
        this.songRepo = songRepo;
        this.albumAssembler = albumAssembler;
    }

    @Transactional
    public void add(AddAlbumRequest addParameter) throws EntityNotFoundException {
        Optional<Artist> artist = this.artistRepo.findById(addParameter.getArtistId());

        Album newAlbum = new AlbumBuilder()
                .albumType(addParameter.getAlbumType())
                .artist(artist.get())
                .releaseDate(addParameter.getReleaseDate())
                .albumName(addParameter.getAlbumName())
                .Build();        

        for (SongIdAndSongNo songIdAndSongNo : addParameter.getSongIds()) {
            Long songId = songIdAndSongNo.getSongId();
            int songNo = songIdAndSongNo.getNo();

            Song song = this.songRepo
                    .findById(songId)
                    .orElseThrow(()-> new EntityNotFoundException(Song.class.getName()));
            
            newAlbum.addSong(song, songNo, false);
        }

        this.albumRepo.save(newAlbum);
    }

    public void changeAlbumName(Long albumId, String newName) {
        Album album = albumRepo.getOne(albumId);
        album.changeName(newName);
    }

    public void delete(Long albumId) {
        albumRepo.deleteById(albumId);
    }

    public Album findById(Long id) {
        return albumRepo.findById(id).orElse(null);
    }

    public List<Album> findByArtistId(Long artistId) {
        return albumRepo.findByArtistId(artistId);
    }

    public List<Album> findByName(String name) {
        return albumRepo.findByName(name);
    }

    public AlbumSimpleViewModel getAlbumSimleViewModelById(Long id) throws EntityNotFoundException {
        AlbumSimpleViewModel viewModel = albumAssembler.albumSimpleViewModel(findById(id));

        return viewModel;
    }

    public List<AlbumSimpleViewModel> findAlbumSimleViewModelByName(String name) {
        List<AlbumSimpleViewModel> albumViewModels = new ArrayList<>();
        for (Album album : findByName(name)) {
            albumViewModels.add(albumAssembler.albumSimpleViewModel(album));
        }

        return albumViewModels;
    }

    public List<AlbumSimpleViewModel> findAlbumSimleViewModelByArtistId(Long artistId) {
        List<AlbumSimpleViewModel> albumViewModels = new ArrayList<>();
        for (Album album : findByArtistId(artistId)) {
            albumViewModels.add(albumAssembler.albumSimpleViewModel(album));
        }

        return albumViewModels;
    }
}
