package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardDTO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;

	@Test
	public void testExist() {
		log.info(service);

		assertNotNull(service);
	}

	/* Register Test */
	@Test
	public void testRegister() {
		BoardDTO dto = new BoardDTO();

		dto.setTitle("새로 시작하는 글");
		dto.setContent("새로 시작하는 내용");
		dto.setWriter("newbie");

		service.register(dto);

		log.info("생성된 게시물의 번호: " + dto.getBno());/* <selectKey>의 리턴 값을 받음 */
	}

	/* Get Test */
	@Test
	public void testGet() {
		BoardDTO dto = service.get(1L);
		if (dto == null) {
			log.info("해당하는 게시물을 찾을 수 없습니다.");
			return;
		}
		log.info(dto);
	}

	/* Delete Test */
	@Test
	public void testDelete() {
		log.info("REMOVE RESULT" + service.remove(2L));
	}

	/* Modify Test */
	@Test
	public void testUpdate() {
		BoardDTO dto = service.get(1L);

		if (dto == null) {
			log.info("해당하는 게시물을 찾을 수 없습니다.");
			return;
		}

		dto.setTitle("제목 수정합니다");
		dto.setContent("내용 수정합니다");

		log.info("UPDATE RESULT" + service.modify(dto));
	}

	/* List Test */
	@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));
	}

}