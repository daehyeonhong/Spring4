package org.zerock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {

	/* Reply Insert Test */
	public int insert(ReplyVO vo);

	/* Reply Read Test */
	public ReplyVO read(Long rno);

	/* Reply Delete Test */
	public int delete(Long rno);

	/* Reply Update Test */
	public int update(ReplyVO vo);

	/* Reply List Test */
	public List<ReplyVO> listWithPaging(@Param("criteria") Criteria criteria, @Param("bno") Long bno);

}