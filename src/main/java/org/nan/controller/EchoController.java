package org.nan.controller;

import org.nan.service.CreateExceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONArray;
import org.json.JSONObject;

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

	@GetMapping(value="/logJson")
	public String logJson() {
		JSONObject object = new JSONObject();
		object.put("id", 1L);
		object.put("name", "Alice");
		object.put("age", 20);

		JSONArray courses = new JSONArray();
		courses.put("Engineering");
		courses.put("Finance");
		courses.put("Chemistry");

		object.put("courses", courses);

		String jsonString = object.toString(2);
		logger.info("Trying to log a json \n Here is the json: \n" + jsonString);
		return "JSON logged";
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
