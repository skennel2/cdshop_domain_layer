package org.almansa.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.almansa.app.domain.album.Album;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AlbumRepository {

    @PersistenceContext
    private EntityManager em;

    public Album findOne(Long id) {
        return em.find(Album.class, id);
    }

    public List<Album> findAll(AlbumSearchCriteria albumSearch){
        String jpql = "SELECT A FROM Album A ";
        TypedQuery<Album> query = em.createQuery(jpql, Album.class);
        return query.getResultList();
    }
    
    public List<Album> findByName(String name) {
        String jpql = "SELECT A FROM Album A WHERE A.name = :name";
        TypedQuery<Album> query = em.createQuery(jpql, Album.class);
        query.setParameter("name", name);

        return query.getResultList();
    }

    public List<Album> findByArtistName(String artistName) {
        String jpql = "SELECT A FROM Album A LEFT JOIN A.albumArtist B WHERE B.name = :name";
        TypedQuery<Album> query = em.createQuery(jpql, Album.class);
        query.setParameter("name", artistName);

        return query.getResultList();
    }

    public void save(Album album) {
        if (album.isNew()) {
            em.persist(album);
        } else {
            em.merge(album);
        }
    }
}
