package org.almansa.app.service;

import java.util.List;

import org.almansa.app.domain.album.Lable;
import org.almansa.app.repository.LableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LableServiceImpl implements LableService {

    @Autowired
    private LableRepository lableRepo;

    @Override
    public void addLable(Lable lable) {
        if (lable == null) {
            throw new IllegalArgumentException("lable is null");
        }

        lableRepo.save(lable);
    }

    @Override
    public void addLable(String lableName) {
        if (lableName == null) {
            throw new IllegalArgumentException("lableName is null");
        }

        Lable lable = new Lable();
        lable.changeName(lableName);

        lableRepo.save(lable);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Lable> getByCeoName(String ceoName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Lable getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Lable> getByName(String name) {
        return lableRepo.findByName(name);
    }
}