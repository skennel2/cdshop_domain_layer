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

	@Autowired
	ApplicationUserValidator userValidator;

	public void joinUser(UserJoinRequest request) {
		ApplicationUser applicationUser = new ApplicationUser(request.getName(), request.getLoginId(),
				request.getPassword());

		PersonalInfomation personalInfomation = new PersonalInfomation(applicationUser,
				new EmailAddress(request.getEmail()), request.getBornDate());				
		applicationUser.setPersonalInfomation(personalInfomation);

		userValidator.verifyValidation(applicationUser);

		userRepo.save(applicationUser);
	}

	public void deleteUser(long id) {
		userRepo.deleteById(id);
	}
}