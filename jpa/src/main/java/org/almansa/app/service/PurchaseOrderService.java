package org.almansa.app.service;

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
    
    public void orderMerchandise(Long userId, Long merchandiseId) {
        
    }
}
