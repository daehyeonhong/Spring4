package org.zerock.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyVO {

	private Long rno, bno;
	private String reply, replyer;
	private Date reply_date, update_date;

}