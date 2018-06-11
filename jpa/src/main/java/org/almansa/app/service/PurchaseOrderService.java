package org.almansa.app.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.almansa.app.domain.exception.RemainingStockException;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.order.PurchaseOrder;
import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.repository.AlbumMerchandiseRepository;
import org.almansa.app.repository.ApplicationUserRepository;
import org.almansa.app.repository.PurchaseOrderRepository;
import org.almansa.app.service.dto.SingleOrderRequest;
import org.almansa.app.service.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class PurchaseOrderService extends ServiceBase {

    private AlbumMerchandiseRepository merchandiseRepo;
    private PurchaseOrderRepository orderRepo;
    private ApplicationUserRepository userRepo;

    @Autowired
    public PurchaseOrderService(PurchaseOrderRepository orderRepo, ApplicationUserRepository userRepo,
            AlbumMerchandiseRepository merchandiseRepo) {
        super();
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.merchandiseRepo = merchandiseRepo;
    }

    @Transactional
    public void orderMerchandise(Long userId, List<SingleOrderRequest> requests) throws EntityNotFoundException, OrderException {
        ApplicationUser orderer = userRepo.getOne(userId);
        PurchaseOrder order = new PurchaseOrder(orderer, null, new Date());

        for (SingleOrderRequest request : requests) {
            AlbumMerchandise merchandise = merchandiseRepo.getOne(request.getMerchandiseId());
            try {
                order.addOrderLine(merchandise, request.getQuantity());                       
                merchandise.removeStock(request.getQuantity());
            }catch(RemainingStockException e) {
                throw new OrderException(e);
            }
        }

        orderRepo.save(order);
    }

    @Transactional
    private void orderSingleMerchandise(ApplicationUser orderer, SingleOrderRequest orderRequest) throws EntityNotFoundException, OrderException {
        AlbumMerchandise merchandise = merchandiseRepo.getOne(orderRequest.getMerchandiseId());

        PurchaseOrder order = new PurchaseOrder(orderer, null, new Date());
        try {
            order.addOrderLine(merchandise, orderRequest.getQuantity());
            merchandise.removeStock(orderRequest.getQuantity());
        }catch(RemainingStockException e) {
            throw new OrderException(e);
        }
        orderRepo.save(order);
    }

    @Transactional
    public void orderSingleMerchandise(Long userId, SingleOrderRequest orderRequest) throws EntityNotFoundException, OrderException {
        ApplicationUser orderer = userRepo.getOne(userId);
        orderSingleMerchandise(orderer, orderRequest);
    }
}