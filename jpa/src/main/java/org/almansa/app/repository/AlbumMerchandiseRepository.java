package org.almansa.app.repository;

import javax.transaction.Transactional;

import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AlbumMerchandiseRepository extends JpaRepository<AlbumMerchandise, Long> {
}
