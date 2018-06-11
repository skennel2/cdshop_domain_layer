package org.almansa.app.service;

import javax.persistence.EntityNotFoundException;

import org.almansa.app.domain.merchandise.MerchandiseBase;
import org.almansa.app.domain.value.Money;
import org.almansa.app.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseService extends ServiceBase {
    
    private MerchandiseRepository merchanRepo;

    @Autowired
    public MerchandiseService(MerchandiseRepository merchanRepo) {
        super();
        this.merchanRepo = merchanRepo;
    }

    public void removeStock(Long merchandiseId, Long amount) throws EntityNotFoundException {
        MerchandiseBase merchandise = merchanRepo
                .findById(merchandiseId)
                .orElseThrow(()-> new EntityNotFoundException());
        
        merchandise.removeStock(amount);
    }
    
    public void addStock(Long merchandiseId, Long amount) throws EntityNotFoundException {
        MerchandiseBase merchandise = merchanRepo
                .findById(merchandiseId)
                .orElseThrow(()-> new EntityNotFoundException());
        
        merchandise.addStock(amount);
    }

    public void changeProductPrice(Long productId, Money newPrice) throws EntityNotFoundException {
        MerchandiseBase merchandise = merchanRepo
                .findById(productId)
                .orElseThrow(()-> new EntityNotFoundException());
        
        merchandise.changePrice(newPrice);
    }    
}
