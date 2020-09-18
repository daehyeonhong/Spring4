package org.zerock.service;

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
public class SampleTxServiceTests {

	@Setter(onMethod_ = @Autowired)
	private SampleTxService sampleTxService;

	@Test
	public void testLog() {
		String str = "Strray\r\nStrray night\r\nPaint your pallete blue and grey Look out on a Summer's Day";
		log.info(str.getBytes().length);

		sampleTxService.addData(str);
	}

}