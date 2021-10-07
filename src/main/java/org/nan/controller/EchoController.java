package org.nan.controller;

import org.nan.service.CreateExceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EchoController {
	Logger logger = LoggerFactory.getLogger(EchoController.class);

	@Autowired
	CreateExceptionService expService;

	@RequestMapping(value="/")
	public String root() {
		return "It Worked";
	}
	
	@GetMapping(path="/echo/{message}")
	public String echo(@PathVariable("message") String message) {
		logger.debug(message);
		return message;
	}

	@GetMapping(path="/createError")
	public String createRunTimeException() {
		try {
			expService.createException();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return "Exception logged";
	}
	
}
