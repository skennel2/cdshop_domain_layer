package org.almansa.app.service;

import java.util.Date;
import java.util.List;

import org.almansa.app.domain.album.Lable;
import org.almansa.app.repository.LableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LableService extends ServiceBase{

    @Autowired
    private LableRepository lableRepo;

    public void add(Lable lable) {
        if (lable == null) {
            throw new IllegalArgumentException("lable can't be null");
        }

        lableRepo.save(lable);
    }

    public void add(String lableName, String ceoName, Date establishmentDate) {
        if (lableName == null) {
            throw new IllegalArgumentException("lableName can't be null");
        }

        Lable lable = new Lable(lableName, ceoName, establishmentDate);
        lableRepo.save(lable);
    }

    public void delete(Long id) {
        lableRepo.deleteById(id);
    }

    public List<Lable> findByCeoName(String ceoName) {
        return lableRepo.findByCeoName(ceoName);
    }
    
    public Lable findById(Long id) {
        return lableRepo.findById(id).orElse(null);
    }

    public List<Lable> findByName(String name) {
        return lableRepo.findByName(name);
    }
    
    public List<Lable> findAll() {
        return lableRepo.findAll();
    }
    
}