package org.almansa.app.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.order.PurchaseOrder;
import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.repository.AlbumMerchandiseRepository;
import org.almansa.app.repository.ApplicationUserRepository;
import org.almansa.app.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService extends ServiceBase {

    private PurchaseOrderRepository orderRepo;
    private ApplicationUserRepository userRepo;
    private AlbumMerchandiseRepository merchandiseRepo;

    @Autowired
    public PurchaseOrderService(PurchaseOrderRepository orderRepo, ApplicationUserRepository userRepo,
            AlbumMerchandiseRepository merchandiseRepo) {
        super();
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.merchandiseRepo = merchandiseRepo;
    }

    @Transactional
    public void orderSingleMerchandise(Long userId, Long merchandiseId, int quantity) {
        ApplicationUser orderer = userRepo.getOne(userId);
        AlbumMerchandise merchandise = merchandiseRepo.getOne(merchandiseId);

        PurchaseOrder order = new PurchaseOrder(orderer, null, new Date());
        order.addOrderLine(merchandise, 1);

        merchandise.removeStock(quantity);

        orderRepo.save(order);
    }
}
