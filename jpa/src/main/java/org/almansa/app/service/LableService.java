package org.almansa.app.service;

import java.util.List;

import org.almansa.app.domain.album.Lable;

public interface LableService {

    void addLable(Lable lable);

    void addLable(String lableName);

    void delete(Long id);

    List<Lable> getByCeoName(String ceoName);

    Lable getById(Long id);

    List<Lable> getByName(String name);
}