package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
@AllArgsConstructor /* 모든 매개변수 생성자 */
@NoArgsConstructor /* 기본 생성자 */
public class SampleVO {

	private Integer mno;

	private String firstName, lastName;

}