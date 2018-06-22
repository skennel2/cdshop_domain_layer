package org.almansa.app.service;

import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.service.exception.ApplicationUserValidationException;

public interface ApplicationUserValidator {
    boolean isValid(ApplicationUser user);

    void verifyValidation(ApplicationUser user) throws ApplicationUserValidationException;
}