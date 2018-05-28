package org.almansa.app.repository;

import java.util.List;

import org.almansa.app.domain.album.Lable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LableRepository extends JpaRepository<Lable, Long> {
    List<Lable> findByCeoName(String ceoName);

    List<Lable> findByName(String name);
}