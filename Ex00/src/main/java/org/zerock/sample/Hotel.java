package org.zerock.sample;

import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.ToString;

@Component
@Getter /* Getter_Method 자동 생성 */
@ToString /* toString_Method 자동 생성 */
public class Hotel {

	private Chef chef;/* Object, Spring 4.3_Ver 이후부터 @Autowired 표시 없이 자동 주입 됨. */

	private String name;/* Object */

}