package org.zerock.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
public class ReplyMapperTests {

	private Long[] bnoArray = { 322L, 321L, 309L, 308L, 307L };

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper replyMapper;

	@Ignore
	@Test
	public void testMapper() {
		log.info("TEST::TEST::TEST::TEST::TEST::TEST");
		log.info("mapper: ==> " + replyMapper);
	}

	@Test
	public void testCreate() {
		log.info("TEST::Create");

		for (int i = 0; i < 10; i++) {
			ReplyVO vo = new ReplyVO();

			/* 게시물 번호 */
			vo.setBno(bnoArray[i % 5]);
			vo.setReply("댓글 테스트::" + i);
			vo.setReplyer("replyer::" + i);

			replyMapper.insert(vo);
		}
	}

}