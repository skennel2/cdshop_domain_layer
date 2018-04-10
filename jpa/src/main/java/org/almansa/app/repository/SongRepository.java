package org.almansa.app.repository;

import org.almansa.app.domain.album.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long>{

}
