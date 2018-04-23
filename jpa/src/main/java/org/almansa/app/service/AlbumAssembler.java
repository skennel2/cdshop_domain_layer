package org.almansa.app.service;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.dto.AlbumSimpleViewModel;

public interface AlbumAssembler {
    AlbumSimpleViewModel albumSimpleViewModel(Album album);
}
