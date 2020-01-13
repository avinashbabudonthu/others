package com.rest.clients.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/get/v1")
public class GetController {

	@GetMapping(value = "/api-1", produces = MediaType.TEXT_PLAIN_VALUE)
	public String api1() {
		log.info("api-1");
		return "Hello World";
	}

}