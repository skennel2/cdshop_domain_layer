package org.almansa.app.repository;

import org.almansa.app.domain.order.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{
}
