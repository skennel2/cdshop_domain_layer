package org.almansa.app.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.user.PersonalInfomation;
import org.almansa.app.domain.value.EmailAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class ApplicationUserPersistenceTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void deleteTest() {
        // persistence
        ApplicationUser user = new ApplicationUser("skennel", "skennel", "1234");

        String persistedEmailAddress = "skennel2@gmail.com";
        PersonalInfomation infomation 
            = new PersonalInfomation(user, new EmailAddress(persistedEmailAddress), new Date());
        
        user.setPersonalInfomation(infomation);
        em.persist(user);
        em.flush();

        // assign
        ApplicationUser userGet = em.find(ApplicationUser.class, user.getId());

        // action
        PersonalInfomation infomationGet = userGet.getPersonalInfomation();
        String emailAddress = infomationGet.getEmail().getEmailAddress();

        // assert
        assertEquals(persistedEmailAddress, emailAddress);
    }

    @Test
    public void persistTest() {
        // persistence
        ApplicationUser user = new ApplicationUser("skennel", "skennel", "1234");

        String persistedEmailAddress = "skennel2@gmail.com";
        PersonalInfomation infomation = new PersonalInfomation(user, new EmailAddress(persistedEmailAddress),
                new Date());
        user.setPersonalInfomation(infomation);
        em.persist(user);
        em.flush();

        // assign
        ApplicationUser userGet = em.find(ApplicationUser.class, user.getId());

        // action
        PersonalInfomation infomationGet = userGet.getPersonalInfomation();
        String emailAddress = infomationGet.getEmail().getEmailAddress();

        // assert
        assertEquals(persistedEmailAddress, emailAddress);
    }
}
