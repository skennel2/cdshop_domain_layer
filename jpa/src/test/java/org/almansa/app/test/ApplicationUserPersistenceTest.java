package org.almansa.app.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.user.PersonalInfomation;
import org.almansa.app.domain.value.EmailAddress;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class ApplicationUserPersistenceTest {

    @Autowired
    private EntityManager em;
    
    @Test
    public void getTest() {
        ApplicationUser user = new ApplicationUser("skennel", "skennel", "1234");
        em.persist(em);
        
        PersonalInfomation infomation = new PersonalInfomation(user, new EmailAddress("skennel2@gmail.com"), new Date(), "123");
        em.persist(infomation);
        
        user.setPersonalInfomation(infomation);
        
        //em.find(ApplicationUser.class, user.getId());
    }
}
