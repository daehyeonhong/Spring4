package org.zerock.service;

import java.util.List;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService {

	/* Reply Insert Test */
	int insert(ReplyVO vo);

	/* Reply Read Test */
	ReplyVO read(Long rno);

	/* Reply Delete Test */
	int delete(Long rno);

	/* Reply Update Test */
	int update(ReplyVO vo);

	/* Reply List Test */
	List<ReplyVO> listWithPaging(Criteria criteria, Long bno);

}