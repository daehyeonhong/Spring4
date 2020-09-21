package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private ReplyMapper replyMapper;

	@Override
	public int insert(ReplyVO vo) {
		log.info("Register..." + vo);
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO read(Long rno) {
		log.info("Read..." + rno);
		return replyMapper.read(rno);
	}

	@Override
	public int delete(Long rno) {
		log.info("Delete..." + rno);
		return replyMapper.delete(rno);
	}

	@Override
	public int update(ReplyVO vo) {
		log.info("Update..." + vo);
		return replyMapper.update(vo);
	}

	@Override
	public ReplyPageDTO listWithPaging(Criteria criteria, Long bno) {
		log.info("ListWithPaging...::Criteria::==>" + criteria);
		log.info("ListWithPaging...::bno::==>" + bno);
		return new ReplyPageDTO(replyMapper.countByBno(bno), replyMapper.listWithPaging(criteria, bno));
	}

}
