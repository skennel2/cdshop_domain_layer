package org.almansa.app.service;

import java.util.List;

import org.almansa.app.domain.Lable;
import org.almansa.app.repository.LableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LableService {
	
	@Autowired
	private LableRepository lableRepo;
	
	public void addLable(Lable lable) {
		if(lable != null) {
			lableRepo.save(lable);	
		}		
	}
	
	public List<Lable> getByName(String name) {
		return lableRepo.findByName(name);
	}
}
