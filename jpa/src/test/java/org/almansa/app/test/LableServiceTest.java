package org.almansa.app.test;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.service.LableService;
import org.almansa.app.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class LableServiceTest {
    @Autowired
    private LableService lableService;
    
    @Test
    public void lableServiceAddTest() {
       lableService.addLable("YG", "yang h.s", DateUtil.toDate(1999, 1, 12));
       Lable lable =  lableService.getByName("YG").get(0);
       assertEquals("yang h.s", lable.getCeoName());
       
       Lable lable2 = new Lable("SM", "lee s.m", null);
       lableService.addLable(lable2);
       lable2 = lableService.getByCeoName("lee s.m").get(0);
       assertEquals("SM", lable2.getName());  
    }
}
