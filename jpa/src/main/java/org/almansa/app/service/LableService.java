package org.almansa.app.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.almansa.app.domain.album.Lable;
import org.almansa.app.repository.LableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LableService extends ServiceBase{

    @Autowired
    private LableRepository lableRepo;

    public void addLable(Lable lable) {
        if (lable == null) {
            throw new IllegalArgumentException("lable can't be null");
        }

        lableRepo.save(lable);
    }

    public void addLable(String lableName, String ceoName, Date establishmentDate) {
        if (lableName == null) {
            throw new IllegalArgumentException("lableName can't be null");
        }

        Lable lable = new Lable(lableName, ceoName, establishmentDate);
        lableRepo.save(lable);
    }

    public void delete(Long id) {
        lableRepo.deleteById(id);
    }

    public List<Lable> getByCeoName(String ceoName) {
        return lableRepo.findByCeoName(ceoName);
    }
    
    public Lable getById(Long id) throws EntityNotFoundException {
        return lableRepo.getOne(id);
    }

    public List<Lable> getByName(String name) {
        return lableRepo.findByName(name);
    }
    
    public List<Lable> getAll() {
        return lableRepo.findAll();
    }
    
}