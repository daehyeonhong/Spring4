package org.zerock.sample;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

/* JUnit_Test (Method) */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ChefTests {

	@Setter(onMethod_ = @Autowired)
	private Restaurant restaurant;

	/* if(restaurant != null) */
	@Test
	public void testExist() {
		/* JUnit에서 실행 하는 Method --> assert*() */
		assertNotNull(restaurant);/* JUnit_Test_Method assertNotNull(Object): Object == null? */
		log.info(restaurant);
		log.info("───────────────────────");
		log.info(restaurant.getChef());
	}

}