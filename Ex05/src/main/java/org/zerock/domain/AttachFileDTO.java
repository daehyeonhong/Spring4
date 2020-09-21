package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttachFileDTO {

	private String fileName, uploadPath, uuid;
	private boolean image;

}