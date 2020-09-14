package org.zerock.mapper;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardDTO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Ignore
	@Test /* 해당 Method를 실행 */
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}

	@Ignore
	@Test
	public void testInsert() {
		BoardDTO dto = new BoardDTO();
		dto.setTitle("MyBatis Title");
		dto.setContent("MyBatis Content");
		dto.setWriter("MyBatis Writer");

		mapper.insert(dto);

		log.info(dto);
	}

	@Ignore
	@Test
	public void testInsertSelectKey() {
		BoardDTO dto = new BoardDTO();
		dto.setTitle("MyBatis Title SelectKey");
		dto.setContent("MyBatis Content SelectKey");
		dto.setWriter("MyBatis Writer SelectKey");

		mapper.insertSelectKey(dto);

		log.info(dto);
	}

	@Test
	public void testGetBoard() {
		BoardDTO dto = mapper.read(5L);
		log.info(dto);
	}

	@Ignore
	@Test
	public void testDelete() {
		log.info("Delete Count: " + mapper.delete(1L));
	}

	@Ignore
	@Test
	public void testUpdate() {
		BoardDTO dto = new BoardDTO();
		dto.setBno(5L);
		dto.setTitle("1수정된 제목");
		dto.setContent("1수정된 내용");
		dto.setWriter("1수정된 작성자");

		int count = mapper.update(dto);

		log.info("UPDATECOUNT" + count);
	}

	@Test
	public void testSearch() {
		Criteria criteria = new Criteria();
		criteria.setKeyword("새로");
		criteria.setType("TC");

		List<BoardDTO> list = mapper.getListA(criteria);
		list.forEach(board -> log.info(board));

		int total = mapper.getTotal(criteria);
		log.info("전체 건수: " + total);
	}

}