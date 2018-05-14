package org.almansa.app.service;

import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.user.PersonalInfomation;
import org.almansa.app.domain.value.EmailAddress;
import org.almansa.app.repository.ApplicationUserRepository;
import org.almansa.app.service.dto.UserJoinRequest;
import org.almansa.app.service.exception.ApplicationUserJoinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService extends ServiceBase {

	@Autowired
	private ApplicationUserRepository userRepo;

	public void joinUser(UserJoinRequest request) {
		if(!EmailAddress.isFormatValid(request.getEmail())) {
			throw new ApplicationUserJoinException("check email address");
		}
		
		ApplicationUser applicationUser = new ApplicationUser(request.getName(), request.getLoginId(),
				request.getPassword());
		EmailAddress email = new EmailAddress(request.getEmail());		
		PersonalInfomation personalInfomation = new PersonalInfomation(applicationUser, email, request.getBornDate());

		applicationUser.setPersonalInfomation(personalInfomation);
		userRepo.save(applicationUser);
	}	
}