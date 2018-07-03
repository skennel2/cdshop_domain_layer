package org.almansa.app.repository;

import java.util.List;

import org.almansa.app.domain.album.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query("SELECT A FROM Artist A WHERE A.name = :name")
    List<Artist> findByName(@Param("name") String name);
}
