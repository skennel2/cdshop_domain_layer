package org.almansa.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.almansa.app.domain.Lable;
import org.almansa.app.repository.LableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LableService {
	
	@Autowired
	private LableRepository lableRepo;
	
	public void addLable(Lable lable) {
		if(lable == null) {
			throw new IllegalArgumentException("lable is null");
		}	
		
		lableRepo.save(lable);	
	}
	
	public void addLable(String lableName) {
		if(lableName == null) {
			throw new IllegalArgumentException("lableName is null");
		}	
		
		Lable lable = new Lable();
		lable.changeName(lableName);			
		
		lableRepo.save(lable);	
	}
	
	public List<Lable> getByName(String name) {
		return lableRepo.findByName(name);
	}
}
