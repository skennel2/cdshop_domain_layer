package org.almansa.app.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.almansa.app.domain.album.Lable;
import org.almansa.app.repository.LableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LableService extends ServiceBase{

    @Autowired
    private LableRepository lableRepo;

    public void add(Lable lable) throws NullPointerException {
        Objects.requireNonNull(lable, "lable can't be null");
        
        lableRepo.save(lable);
    }

    public void add(String lableName, String ceoName, Date establishmentDate) throws NullPointerException {
        Objects.requireNonNull(lableName, "lableName can't be null");

        Lable lable = new Lable(lableName, ceoName, establishmentDate);
        lableRepo.save(lable);
    }

    public void delete(Long id) throws EmptyResultDataAccessException {
        try {
            lableRepo.deleteById(id);
        }catch(EmptyResultDataAccessException e) {
            throw e;
        }
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