package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardDTO;
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

	@GetMapping("/list")
	public void list(Model model) {
		log.info("list:");

		model.addAttribute("list", service.getList());/* service의 getList()Method 호출 결과를 model에 저장 */
	}

	/* @GetMapping("/get") */
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("/get or /modify");

		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardDTO dto, RedirectAttributes redirectAttributes) {
		log.info("/modify");

		redirectAttributes.addFlashAttribute("result", service.modify(dto) ? "success" : "fail");

		return "redirect:/board/list";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes redirectAttributes) {
		log.info("/remove");

		redirectAttributes.addFlashAttribute("result", service.remove(bno) ? "success" : "fail");

		return "redirect:/board/list";
	}

}