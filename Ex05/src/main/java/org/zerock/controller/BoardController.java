package org.zerock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardDTO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*") /* ClassLevel Annotation */
public class BoardController {

	private BoardService service;

	@GetMapping("/register") /* URL: ==> /board/register */
	public void register() {
	}/* voidType Method의 경우 URI와 같은 jsp로 이동 ==> register.jsp */

	@PostMapping("/register")
	public String register(BoardDTO dto, RedirectAttributes redirectAttributes) {
		service.register(dto);

		redirectAttributes.addFlashAttribute("result", dto.getBno());

		return "redirect:/board/list";
	}

	/*
	 * @GetMapping("/list") public void list(Model model) { log.info("list:");
	 * 
	 * model.addAttribute("list", service.getList()); }
	 */
	/* service의 getList()Method 호출 결과를 model에 저장 */

	/*
	 * @GetMapping("/list") public void list(Criteria criteria, Model model) {
	 * log.info("list: " + criteria);
	 * 
	 * model.addAttribute("list", service.getListA(criteria)); }
	 */

	@GetMapping("/list")
	public void list(Criteria criteria, Model model) {
		log.info("list: " + criteria);

		model.addAttribute("list", service.getListA(criteria));

		int total = service.getTotal(criteria);
		log.info("total: " + total);

		model.addAttribute("pageMaker", new PageDTO(criteria, total));
	}

	/*
	 * @GetMapping({ "/get", "/modify" }) public void get(@RequestParam("bno") Long
	 * bno, Model model) { log.info("/get or /modify");
	 * 
	 * model.addAttribute("board", service.get(bno)); }
	 */
	/* @GetMapping("/get") */
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("criteria") Criteria criteria, Model model) {
		log.info("/get or /modify");

		int total = service.getTotal(criteria);
		model.addAttribute("board", service.get(bno));
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
	}

	@PostMapping("/modify")
	public String modify(BoardDTO dto, @ModelAttribute("criteria") Criteria criteria,
			RedirectAttributes redirectAttributes) {
		log.info("/modify");

		redirectAttributes.addFlashAttribute("result", service.modify(dto) ? "success" : "fail");

		int total = service.getTotal(criteria);
		log.info("total: " + total);

		redirectAttributes.addAttribute("pageNumber", criteria.getPageNumber());
		redirectAttributes.addAttribute("amount", criteria.getAmount());
		redirectAttributes.addAttribute("type", criteria.getType());
		redirectAttributes.addAttribute("keyword", criteria.getKeyword());

		return "redirect:/board/list";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("criterai") Criteria criteria,
			RedirectAttributes redirectAttributes) {
		log.info("/remove");

		redirectAttributes.addFlashAttribute("result", service.remove(bno) ? "success" : "fail");

		redirectAttributes.addAttribute("pageNumber", criteria.getPageNumber());
		redirectAttributes.addAttribute("amount", criteria.getAmount());
		redirectAttributes.addAttribute("type", criteria.getType());
		redirectAttributes.addAttribute("keyword", criteria.getKeyword());

		return "redirect:/board/list";
	}

	// 좋아요
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH }, value = "/good/{bno}")
	public ResponseEntity<BoardDTO> good(@PathVariable("bno") Long bno) {
		log.info(bno);

		service.updateGoodCount(bno);
		return new ResponseEntity<BoardDTO>(service.getGoodBadCnt(bno), HttpStatus.OK);
	}

	// 싫어요
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH }, value = "/bad/{bno}")
	public ResponseEntity<BoardDTO> bad(@PathVariable("bno") Long bno) {
		log.info(bno);

		service.updateBadCount(bno);
		return new ResponseEntity<BoardDTO>(service.getGoodBadCnt(bno), HttpStatus.OK);
	}

}