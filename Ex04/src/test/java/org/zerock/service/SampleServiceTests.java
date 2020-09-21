package org.zerock.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class SampleServiceTests {

	@Setter(onMethod_ = @Autowired)
	private SampleService sampleService;

	@Ignore
	@Test
	public void testClass() {
		log.info(sampleService);
		log.info(sampleService.getClass().getName());
	}

	@Test
	public void testAdd() throws Exception {
		log.info(sampleService.doAdd("123", "456"));
	}

}