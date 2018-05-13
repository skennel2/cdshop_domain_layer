package org.almansa.app.service;

import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.repository.ApplicationUserRepository;
import org.almansa.app.service.dto.UserJoinRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService extends ServiceBase {
    
    @Autowired
    private ApplicationUserRepository userRepo;
    
	public void joinUser(UserJoinRequestModel request) {
	    ApplicationUser user = new ApplicationUser(request.getId(), request.getId(), request.getPassword());
	}
}