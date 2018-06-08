package org.almansa.app.repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.almansa.app.domain.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT A FROM Album A LEFT JOIN A.albumArtist B WHERE B.id = :id")
    List<Album> findByArtistId(@Param("id") Long artistId) throws EntityNotFoundException;

    @Query("SELECT A FROM Album A LEFT JOIN A.albumArtist B WHERE B.name = :name")
    List<Album> findByArtistName(@Param("name") String artistName);

    @Query("SELECT A FROM Album A WHERE A.name = :name")
    List<Album> findByName(@Param("name") String name);

}