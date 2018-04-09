package org.almansa.app.repository;

import org.almansa.app.domain.NamedEntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedEntityRepository<T extends NamedEntityBase, ID> extends JpaRepository<T, ID> {
	T findByName(String name);
}
