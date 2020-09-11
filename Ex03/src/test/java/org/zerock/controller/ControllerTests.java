package org.zerock.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml", /* DAO, Service */
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) /* Controller */
@Log4j
public class ControllerTests {

	/* WebTestConfiguration */
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext webApplicationContext;

	/* WebTest MockUp Object */
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Ignore
	@Test
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
	}

	@Ignore
	@Test
	public void testRegister() throws Exception {
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/register").param("title", "새 글 제목")
						.param("writer", "user00").param("content", "새 글 내용"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}

	@Ignore
	@Test
	public void testGet() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "5")).andReturn()
				.getModelAndView().getModelMap());
	}

	@Ignore
	@Test
	public void testModify() throws Exception {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("bno", "3");
		map.add("writer", "User00");
		map.add("title", "수정된 테스트 새 글 제목");
		map.add("content", "수정된 테스트 새 글 내용");

		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify").params(map)).andReturn()
				.getModelAndView().getViewName();

		log.info(resultPage);
	}

	@Ignore
	@Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno", "6")).andReturn()
				.getModelAndView().getViewName();

		log.info(resultPage);
	}

	@Test
	public void getList() throws Exception {
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.get("/board/list").param("pageNumber", "1").param("amount", "10"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
	}

}