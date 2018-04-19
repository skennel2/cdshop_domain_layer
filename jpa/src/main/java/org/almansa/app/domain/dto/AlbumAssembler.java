package org.almansa.app.domain.dto;

import org.almansa.app.domain.album.Album;

public interface AlbumAssembler {
    AlbumSimpleViewModel albumSimpleViewModel(Album album);
}
