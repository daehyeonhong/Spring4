package org.zerock.mapper;

import java.util.List;
import org.zerock.domain.BoardDTO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	/* @Select("SELECT*FROM table_board WHERE bno>0") */
	public List<BoardDTO> getList();

	public List<BoardDTO> getListA(Criteria criteria);

	/* Insert */
	public void insert(BoardDTO dto);

	/* Sequence_Key를 이용한 Insert */
	public void insertSelectKey(BoardDTO dto);

	/* 글 내용 조회 */
	public BoardDTO read(Long bno);

	/* 삭제 처리 */
	public int delete(Long bno);

	/* 수정처리 */
	public int update(BoardDTO dto);

	/* 전체 조회 건 수 */
	public int getTotal(Criteria criteria);

}