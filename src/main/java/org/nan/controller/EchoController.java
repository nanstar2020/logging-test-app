package org.nan.controller;

import org.nan.service.CreateExceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;


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

	@GetMapping(path="/logLongLine")
	public String createLongLine() {

		//create a string longer than 256k
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 257; i++) {
			for(int j = 0; j < 1024; j++ ) {
				builder.append("X");
			}
		}
		String longLine = builder.toString();
		logger.info("This is the long string " + longLine);
		logger.info("Length of the String " + longLine.length() + " time = "  + System.currentTimeMillis());

		return "String created: " + longLine;
	}

	@GetMapping(path="/logShortLine")
	public String createShortLine() {
		String shortLine = "This is a short line";
		logger.info(shortLine);
		logger.info("Lenght of the String " + shortLine.length() + " time = " + System.currentTimeMillis());

		return "String createed: " + shortLine;
	}

	@GetMapping("/listHeaders")
	public ResponseEntity<String> listAllHeaders( @RequestHeader Map<String, String> headers) {
		StringBuilder sb = new StringBuilder();
		headers.forEach((key, value) -> {
			sb.append(key);
			sb.append(": ");
			sb.append(value);
			sb.append("\n");
			logger.info(String.format("Header '%s' = %s", key, value));
		});

		return new ResponseEntity<String>(
				sb.toString(), HttpStatus.OK);
	}
	
	@GetMapping("/testRetry")
	public ResponseEntity testRetry() {
		logger.info("Enter testRetry at " + LocalTime.now());
//		try {
//			Thread.sleep(5000);
//		} catch (Exception e) {
//
//		}
		logger.info("Exist testRetry at " + LocalTime.now());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
	}

	@GetMapping("/testRetry2")
	public ResponseEntity testRetry2() {
		logger.info("Enter testRetry at " + LocalTime.now());

		logger.info("Exist testRetry at " + LocalTime.now());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}
}
