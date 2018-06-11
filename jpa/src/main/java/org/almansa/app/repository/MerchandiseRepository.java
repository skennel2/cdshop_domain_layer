package org.almansa.app.repository;

import org.almansa.app.domain.merchandise.MerchandiseBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchandiseRepository extends JpaRepository<MerchandiseBase, Long>{

}
