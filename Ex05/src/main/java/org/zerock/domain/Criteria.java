package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	private int pageNumber, amount;

	private String type, keyword;

	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNumber, int amount) {
		this.pageNumber = pageNumber;
		this.amount = amount;
	}

	public String[] getTypeArray() {
		return type == null ? new String[] {} : type.split("");
	}

}