package org.almansa.app.repository;

import java.util.List;

import org.almansa.app.domain.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByName(String name);
}