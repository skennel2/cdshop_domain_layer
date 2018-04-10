package org.almansa.app.repository;

import org.almansa.app.domain.album.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long>{
    
}
