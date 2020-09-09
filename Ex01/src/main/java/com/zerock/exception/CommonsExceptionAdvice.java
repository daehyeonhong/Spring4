package com.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonsExceptionAdvice {

	@ExceptionHandler(Exception.class)
	public String except(Exception exception, Model model) {
		log.error("Exception" + exception.getMessage());
		model.addAttribute("exception", exception);
		log.error(model);
		return "error_page";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handler404(NoHandlerFoundException handlerFoundException) {
		return "custom404";
	}

}