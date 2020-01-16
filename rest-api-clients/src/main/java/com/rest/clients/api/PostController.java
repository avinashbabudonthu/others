package com.rest.clients.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/post/v1")
public class PostController {

	@PostMapping(value = "/api-1", produces = MediaType.TEXT_PLAIN_VALUE)
	public String api1() {
		log.info("/post/v1/api-1");
		return "Hello World Post API 1";
	}
}