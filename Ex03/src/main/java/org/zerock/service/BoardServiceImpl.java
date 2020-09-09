package org.zerock.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardDTO;
import org.zerock.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	/* Spring 4.3 이상에서 자동 D.I */
	private BoardMapper mapper;

	@Override
	public void register(BoardDTO dto) {
		log.info("register..." + dto);

		/* mapper Method 호출 */
		mapper.insertSelectKey(dto);
	}

	@Override
	public BoardDTO get(Long bno) {
		log.info("get..." + bno);

		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardDTO dto) {
		dto.setBno(6L);
		dto.setTitle("또수정");
		dto.setContent("또수정");
		dto.setWriter("또수정");
		log.info("modify..." + dto);

		return mapper.update(dto) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("delete..." + bno);

		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardDTO> getList() {
		log.info("getList");

		/* mapper Method 호출 및 값 리턴 */
		return mapper.getList();
	}

}
