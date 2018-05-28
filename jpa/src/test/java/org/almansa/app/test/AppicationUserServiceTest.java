package org.almansa.app.test;

import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.service.ApplicationUserService;
import org.almansa.app.service.dto.UserJoinRequest;
import org.almansa.app.service.exception.ApplicationUserJoinException;
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
public class AppicationUserServiceTest {

	@Autowired
	private ApplicationUserService service;
	
	@Test(expected = ApplicationUserJoinException.class)
	public void duplicatedLoginIdTest() {
		UserJoinRequest request = new UserJoinRequest();
		request.setName("skennel");
		request.setLoginId("skennel2");
		request.setBornDate(DateUtil.toDate(2000, 1, 2));
		request.setPassword("1234");
		request.setEmail("skennel@naver.com");
		
		service.joinUser(request);
		
		UserJoinRequest request2 = new UserJoinRequest();
		request2.setName("skennel");
		request2.setLoginId("skennel2");
		request2.setBornDate(DateUtil.toDate(2000, 1, 2));
		request2.setPassword("1234");
		request2.setEmail("skennel@naver.com");
		
		service.joinUser(request2);		
	}
	
}
