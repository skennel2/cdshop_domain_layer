package org.almansa.app.repository;

import java.util.List;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    List<Album> findByLoginId(@Param("loginId") String loginId);
}
