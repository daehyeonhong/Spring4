package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

	/* 단순 문자열 반환 시 처리 */
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE" + MediaType.TEXT_PLAIN_VALUE);

		return "안녕하세요";
	}

	/* 사용자 정의 객체 반환 */
	@GetMapping(value = "/getSample", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {

		return new SampleVO(112, "스타", "로드");
	}

	/* 컬렉션 타입 객체 반환 */
	@GetMapping(value = "/getList", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<SampleVO> getList() {
		/*
		 * List<SampleVO> list = new ArrayList<SampleVO>(); for (int i = 0; i < 10; i++)
		 * { list.add(new SampleVO(i + 1, (i + 1) + "First", (i + 1) + "Last")); }
		 */

		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + "Last"))
				.collect(Collectors.toList());
	}

	/* map object 반환 */
	@GetMapping(value = "/getMap", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));

		return map;
	}

	/* ResponseEntity 객체 반환 */
	@GetMapping(value = "check", params = { "height", "weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);

		return height < 150 ? ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo)
				: ResponseEntity.status(HttpStatus.OK).body(vo);
	}

}