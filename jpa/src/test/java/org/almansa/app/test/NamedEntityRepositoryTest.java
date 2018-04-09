package org.almansa.app.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.service.LableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class NamedEntityRepositoryTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private LableService lableService;

	@Test
	public void lableServiceTest() {
		Lable lable = new Lable();
		lable.setCeoName("na yun su");
		lable.setName("almansa");

		lableService.addLable(lable);
	}
}
