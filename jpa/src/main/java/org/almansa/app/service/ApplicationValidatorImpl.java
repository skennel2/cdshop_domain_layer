package org.almansa.app.service;

import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.value.EmailAddress;
import org.almansa.app.repository.ApplicationUserRepository;
import org.almansa.app.service.exception.ApplicationUserJoinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationValidatorImpl implements ApplicationUserValidator{

	@Autowired
	private ApplicationUserRepository userRepo;
	
	@Override
	public boolean isValid(ApplicationUser user) {
	    try {
	        verifyValidation(user);
	    }catch(ApplicationUserJoinException e){
	        return false;
	    }
        
        return true;
	}

	@Override
	public void verifyValidation(ApplicationUser user) throws ApplicationUserJoinException{
	    if(user.getPassword() == null || user.getPassword().trim().equals("")) {
	        throw new ApplicationUserJoinException("password can't be null or empty");
	    }
	    
		if(!EmailAddress.isFormatValid(user.getPersonalInfomation().getEmail().getEmailAddress())) {
			throw new ApplicationUserJoinException("check email address");
		}
		
		if(userRepo.findByLoginId(user.getLoginId()) != null) {
			throw new ApplicationUserJoinException("duplicated id");
		}
	}

}
