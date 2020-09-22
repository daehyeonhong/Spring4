package org.zerock.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardDTO;
import org.zerock.domain.Criteria;
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

	@Transactional
	@Override
	public BoardDTO get(Long bno) {
		log.info("get..." + bno);
		mapper.updateReadCount(bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardDTO dto) {
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

	@Override
	public List<BoardDTO> getListA(Criteria criteria) {
		log.info("getListWithPaging");
		return mapper.getListA(criteria);
	}

	@Override
	public int getTotal(Criteria criteria) {
		log.info("getTotal");
		return mapper.getTotal(criteria);
	}

	@Override
	public int updateGoodCount(Long bno) {
		log.info("good count update...");
		return mapper.updateGoodCount(bno);
	}

	@Override
	public int updateBadCount(Long bno) {
		log.info("bad count update...");
		return mapper.updateBadCount(bno);
	}

	@Override
	public BoardDTO getGoodBadCnt(Long bno) {
		log.info("getGoodBadCnt...");

		return mapper.read(bno);
	}

}
