package org.almansa.app.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager em;

    @Test
    public void lableServiceAddTest() {
        lableService.add("YG", "yang h.s", DateUtil.toDate(1999, 1, 12));
        Lable lable = lableService.findByName("YG").get(0);
        assertEquals("yang h.s", lable.getCeoName());

        Lable lable2 = new Lable("SM", "lee s.m", null);
        lableService.add(lable2);
        lable2 = lableService.findByCeoName("lee s.m").get(0);
        assertEquals("SM", lable2.getName());
    }

    @Test
    public void lableGetByIdTest() {
        lableService.add("YG", "yang h.s", DateUtil.toDate(1999, 1, 12));
        Lable lable = lableService.findByName("YG").get(0);

        em.flush();

        Lable lableGetById = lableService.findById(lable.getId());

        assertEquals(lable.getId(), lableGetById.getId());
    }

    @Test
    public void lableGetByIdNotExistsTest() {
        Lable lableFindById= lableService.findById(Long.valueOf(123123));

        assertEquals(null, lableFindById);
    }

    @Test
    public void lablFindByCeoNameNullTest() {
        List<Lable> lableFindByCeoName = lableService.findByCeoName(null);

        assertEquals(0, lableFindByCeoName.size());
    }

    @Test(expected = NullPointerException.class)
    public void lableFindByIdNullTest() {
        Lable lableGetById = lableService.findById(null);

        assertEquals(null, lableGetById);
    }
}
