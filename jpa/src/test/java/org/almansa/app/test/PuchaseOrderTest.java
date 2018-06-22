package org.almansa.app.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.service.PurchaseOrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
public class PuchaseOrderTest {
    
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PurchaseOrderService service;
    
    @Before
    public void setup() {
    }
    
    @Test
    public void PagingTest() {
    }
}
