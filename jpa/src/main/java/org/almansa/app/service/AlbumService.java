package org.almansa.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumBuilder;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.CategoryTag;
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
    public void AddAlbum(AddAlbumRequest addParameter) {
        Optional<Artist> artist = this.artistRepo.findById(addParameter.getArtistId());

        Album newAlbum = new AlbumBuilder().albumType(addParameter.getAlbumType()).artist(artist.get())
                .releaseDate(addParameter.getReleaseDate()).albumName(addParameter.getAlbumName()).Build();

        for (SongIdAndSongNo songId : addParameter.getSongIds()) {
            Optional<Song> albumSong = this.songRepo.findById(songId.getSongId());

            if (albumSong.isPresent()) {
                newAlbum.addSong(albumSong.get(), songId.getNo(), false);
            }
        }

        for (String tag : addParameter.getTag()) {
            CategoryTag categoryTag = new CategoryTag();
            categoryTag.setName(tag);
            newAlbum.addCategory(categoryTag);
        }

        this.albumRepo.save(newAlbum);
    }

    @Transactional
    public void addTagToAlbum(Long albumId, List<String> newTags) {
        Album album = albumRepo.getOne(albumId);

        CategoryTag newTag = null;
        for (String tag : newTags) {
            newTag = new CategoryTag();
            newTag.setName(tag);

            album.addCategory(newTag);
        }
    }

    @Transactional
    public void changeAlbumName(Long albumId, String newName) {
        Album album = albumRepo.getOne(albumId);
        album.changeName(newName);
    }

    public AlbumSimpleViewModel getAlbumSimleViewModelById(Long id) {
        Album album = albumRepo.getOne(id);
        AlbumSimpleViewModel viewModel = albumAssembler.albumSimpleViewModel(album);

        return viewModel;
    }

    public List<AlbumSimpleViewModel> getAlbumSimleViewModelByName(String name) {
        List<Album> albums = albumRepo.findByName(name);

        List<AlbumSimpleViewModel> albumViewModels = new ArrayList<>();
        for (Album item : albums) {
            albumViewModels.add(albumAssembler.albumSimpleViewModel(item));
        }

        return albumViewModels;
    }
}
