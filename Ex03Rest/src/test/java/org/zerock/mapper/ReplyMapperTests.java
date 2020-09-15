package org.zerock.mapper;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
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

	@Ignore
	@Test
	public void testCreate() {
		log.info("TEST::Create");

		for (int i = 0; i < bnoArray.length * 4; i++) {
			ReplyVO vo = new ReplyVO();

			/* 게시물 번호 */
			vo.setBno(bnoArray[i % bnoArray.length]);
			vo.setReply("댓글 테스트::" + i);
			vo.setReplyer("replyer::" + i);

			replyMapper.insert(vo);
		}
	}

	@Ignore
	@Test
	public void testRead() {
		log.info("TEST::Read");
		log.info(replyMapper.read(1L));
	}

	@Ignore
	@Test
	public void testDelete() {
		log.info("TEST::Delete");
		log.info(replyMapper.delete(4L) == 1 ? "SUCCESS" : "FAILURE");
	}

	@Ignore
	@Test
	public void testUpdate() {
		log.info("TEST::Update");

		ReplyVO reply = new ReplyVO();

		reply.setRno(5L);
		reply.setReply("수정된 댓글");

		log.info(replyMapper.update(reply) == 1 ? "SUCCESS" : "FAILURE");
		log.info(replyMapper.read(reply.getRno()));
	}

	@Test
	public void testListWithPaging() {
		log.info("TEST::ListWithPaging");
		log.info(replyMapper.listWithPaging(new Criteria(), 307L));
	}

}