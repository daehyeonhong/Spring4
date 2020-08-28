package com.zerock.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class TodoDTO {

	private String title;
	private Date dueDate;
	/* 이 형식으로 선언한 후에 Controller에서 @InitBinder 사용하여 format을 맞춤 */

	/* Parameter로 넘어온 문자열 "yyyy/MM/dd" 형태의 값을 Date_Type으로 변환하는 Annotation */
	/* 아래와 같이 선언하면 Controller에서 @InnitBinder 사용하지 않고 Date 변환 */
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date fromDate;

}