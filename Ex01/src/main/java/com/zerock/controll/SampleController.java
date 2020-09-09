package com.zerock.controll;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.zerock.domain.SampleDTO;
import com.zerock.domain.SampleDTOList;
import com.zerock.domain.TodoDTO;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*") /* ClassLevel URL Mapping: /sample/anyURI */
@Log4j
public class SampleController {

	/* ClassLevel URL Mapping 후 MethodLevel에서 빈 문자열 매핑: /sample/ */
	@RequestMapping("")
	public void basic() {
		log.info("Basic..");
	}

	/* ClassLevel URL Mapping 후 MethodLevel /basic ==> /sample/basic */
	/* Method가 void면 URL 경로가 View 명이 됨. basic.jsp */
	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		log.info("Basic Get..");
	}

	/* /sample/basicOnlyGet */
	/* basicOnlyGet.jsp */
	/* @RequestMapping(value = "/basicOnlyGet", method = RequestMethod.GET) */
	/* @PostMapping("/basicOnlyGet") */
	@GetMapping("/basicOnlyGet") /* Spring4.3 이후에 추가된 Mapping Annotation */
	public void basicGet2() {
		log.info("Basic Get Only Get..");
	}

	/* Controller의 Parameter 수집 */
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {/* SampleDTO 속성명과 같은 Parameter는 AutoMapping되어서 넘어옴 */
		log.info("" + dto);

		return "sample/ex01";/* Views/ex01.jsp */
	}

	/* @RequestParam("Parameter_Name") TypeVariable_Name */
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name: " + name);
		log.info("age: " + age);

		return "/sample/ex02";
	}

	/* Parameter로 넘어온 객체를 List로 받기 */
	/* 동일한 이름으로 여러개 값이 넘어오는 경우 ?ids=value1&ids=value2 */
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids, Model model) {
		log.info("ids: " + ids);
		model.addAttribute("ids", ids);

		return "sample/ex02List";
	}

	@RequestMapping("ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids, Model model) {
		log.info("array ids: " + Arrays.toString(ids));/* 배열을 문자열로 변환 Arrays.toString()_Method */
		model.addAttribute("ids", ids);
		return "sample/ex02Array";
	}

	/* ?list[0].name=value1&list[1].name=value2 */
	@GetMapping("ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list DTOs: " + list);

		return "sample/ex02Bean";
	}

	/* 문자열 형태로 넘어온 Parameter 값을 Date_Type에 맞추는 설정 Annotation */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);

		return "ex03";
	}

	/* View로 Data 전달 */
	/* Parameter로 넘어온 속성값이 Object 속성으로 Mapping되어 넘어와, View까지 전달(Case:Objcet) */
	/* 기본타입은 넘어온 값이 view까지 전달 안 됨, @ModelAttribute()Annotation으로 View까지 전달 가능 */
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto: " + dto);
		log.info("page: " + page);
		return "sample/ex04";
	}

	/* RequestMapping_Method에서 Object를 return하는 경우 */
	/* @ResponseBody 사용시 pom.xml에 jackson-databind Module 추가 */
	@RequestMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06..");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");

		return dto;
	}

	/* Object 응답으로 전달할 때 Body와 Header>Status 상태값만 전달하는 방식 */
	/* ResponseEntity_Object를 이용하여 응답 */
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("/ex07..");

		String message = "{\"name\":\"홍길동\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");

		return new ResponseEntity<String>(message, header, HttpStatus.OK);
	}

	/* FileUpload */
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload..");
	}

	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(t -> {
			log.info("─────────────────");
			log.info("name: " + t.getOriginalFilename());
			log.info("size" + t.getSize());
			long now = new Date().getTime();
			try {
				String originalFileName = t.getOriginalFilename();
				String fileName = originalFileName.substring(0, originalFileName.indexOf("."));
				String extra = originalFileName.substring(originalFileName.indexOf("."));
				t.transferTo(new File("c:\\upload\\" + fileName + now + extra));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		});
		/*
		 * { files.forEach(new Consumer<MultipartFile>() {
		 * 
		 * @Override public void accept(MultipartFile t) {
		 * log.info("─────────────────"); log.info("name: " + t.getOriginalFilename());
		 * log.info("size" + t.getSize()); } }); }
		 */
	}
}