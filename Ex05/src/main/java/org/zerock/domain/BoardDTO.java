package org.zerock.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {

	private long bno;
	private int read_count, reply_count, good, bad;
	private String title, content, writer;
	private Date regDate, updateDate;

}