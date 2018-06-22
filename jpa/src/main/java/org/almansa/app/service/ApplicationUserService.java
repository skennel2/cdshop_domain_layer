package org.almansa.app.service;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.user.PersonalInfomation;
import org.almansa.app.domain.value.EmailAddress;
import org.almansa.app.repository.ApplicationUserRepository;
import org.almansa.app.service.dto.UserJoinRequest;
import org.almansa.app.service.exception.ApplicationUserValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService extends ServiceBase {

    @Autowired
    private ApplicationUserRepository userRepo;

    @Autowired
    ApplicationUserValidator userValidator;

    @Transactional
    public void joinUser(UserJoinRequest request) throws ApplicationUserValidationException {
        try {
            ApplicationUser applicationUser = 
                    new ApplicationUser(request.getName(), request.getLoginId(), request.getPassword());
            EmailAddress email = new EmailAddress(request.getEmail());
            PersonalInfomation personalInfomation = 
                    new PersonalInfomation(applicationUser, email, request.getBornDate());

            applicationUser.setPersonalInfomation(personalInfomation);
            userValidator.verifyValidation(applicationUser);

            userRepo.save(applicationUser);
        } catch (ApplicationUserValidationException e) {
            throw e;
        } catch (NullPointerException e) {
            throw new ApplicationUserValidationException("required value is null", e);
        } catch (IllegalArgumentException e) {
            throw new ApplicationUserValidationException("required value is illegal", e);
        }
    }

    public void changeNameForce(long id, String newName) throws EntityNotFoundException, ApplicationUserValidationException {
        ApplicationUser user = findUserById(id);

        if (Objects.isNull(user)) {
            throw new EntityNotFoundException("can't found user");
        }
        
        String oldName = user.getName();        
        user.changeName(newName);
        
        try {
            userValidator.verifyValidation(user);
        }catch(ApplicationUserValidationException e) {
            user.changeName(oldName);            
            throw e;
        }
    }
    
    public void changePassword(long id, String newName) throws EntityNotFoundException, ApplicationUserValidationException {
        ApplicationUser user = findUserById(id);

        if (Objects.isNull(user)) {
            throw new EntityNotFoundException("can't found user");
        }
        
        String oldName = user.getName();        
        user.changeName(newName);
        
        try {
            userValidator.verifyValidation(user);
        }catch(ApplicationUserValidationException e) {
            user.changeName(oldName);            
            throw e;
        }
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    public boolean isAbleToLogin(String loginId, String password) {
        ApplicationUser user = findUserByLoginId(loginId);
        if (Objects.nonNull(user) && user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }

    public List<ApplicationUser> findAll() {
        return userRepo.findAll();
    }

    public ApplicationUser findUserById(long id) {
        return userRepo.findById(id).orElse(null);
    }

    public ApplicationUser findUserByLoginId(String loginId) {
        return userRepo.findByLoginId(loginId);
    }
}