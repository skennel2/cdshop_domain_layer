package org.almansa.app.domain.dto;

import org.almansa.app.domain.album.Album;
import org.springframework.stereotype.Component;

@Component
public class AlbumAssemblerImpl implements AlbumAssembler{

    @Override
    public AlbumSimpleViewModel albumSimpleViewModel(Album album) {
        AlbumSimpleViewModel viewModel = new AlbumSimpleViewModel();
        
        viewModel.setAlbumId(album.getId());
        viewModel.setAlbumName(album.getName());
        viewModel.setArtistName(album.getAlbumArtist().getName());
        viewModel.setReleaseDate(album.getReleaseDate());
        viewModel.setAlbumType(album.getAlbumType());
        viewModel.setContainingSongCount(album.getNumberOfContainingSongs());
        
        return viewModel;
    }
}