package org.almansa.app.service;

import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.value.EmailAddress;
import org.almansa.app.repository.ApplicationUserRepository;
import org.almansa.app.service.exception.ApplicationUserValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ApplicationValidatorImpl implements ApplicationUserValidator {

    @Autowired
    private ApplicationUserRepository userRepo;

    @Override
    public boolean isValid(ApplicationUser user) {
        try {
            verifyValidation(user);
        } catch (ApplicationUserValidationException e) {
            return false;
        }

        return true;
    }

    @Override
    public void verifyValidation(ApplicationUser user) throws ApplicationUserValidationException {
        if (!StringUtils.hasText(user.getPassword())) {
            throw new ApplicationUserValidationException("password can't be null or empty");
        }

        if (!EmailAddress.isFormatValid(user.getPersonalInfomation().getEmail().getEmailAddress())) {
            throw new ApplicationUserValidationException("check email address");
        }

        if (userRepo.findByLoginId(user.getLoginId()) != null) {
            throw new ApplicationUserValidationException("duplicated id");
        }
    }
}
