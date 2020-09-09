package org.zerock.service;

import java.util.List;
import org.zerock.domain.BoardDTO;

public interface BoardService {

	/* 글 입력 */
	public void register(BoardDTO dto);

	/* 글 조회 */
	public BoardDTO get(Long bno);

	/* 글 수정 */
	public boolean modify(BoardDTO dto);

	/* 글 삭제 */
	public boolean remove(Long bno);

	/* 글 목록 조회 */
	public List<BoardDTO> getList();

}