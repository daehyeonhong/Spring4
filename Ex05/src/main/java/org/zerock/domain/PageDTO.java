package org.zerock.domain;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class PageDTO {

	private int startPage, endPage, total;

	private boolean prev, next;

	private Criteria criteria;

	/* Constructor */
	public PageDTO(Criteria criteria, int total) {
		this.criteria = criteria;
		this.total = total;

		/* Paging 신식처리 */
		this.endPage = (int) Math.ceil(criteria.getPageNumber() / 10.0) * 10;

		this.startPage = this.endPage - 9;

		/* 실제 Last Page */
		int realEnd = (int) (Math.ceil((this.total * 1.0) / criteria.getAmount()));

		/* LastPage Revision */
		if (realEnd <= this.endPage) {
			this.endPage = realEnd;
		}

		/* PrevPage, NextPage */
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;

	}

}