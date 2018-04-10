package org.almansa.app.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AlbumMerchandiseRepository {
    @PersistenceContext
    private EntityManager em;
}
