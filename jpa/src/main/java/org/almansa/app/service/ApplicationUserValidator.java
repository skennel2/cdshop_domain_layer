package org.almansa.app.service;

import org.almansa.app.domain.user.ApplicationUser;

public interface ApplicationUserValidator{
	boolean isValid(ApplicationUser user);
	
	void verifyValidation(ApplicationUser user);
}