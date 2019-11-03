package com.lombok.practice;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4JAnnotation {

	@Test
	public void method1() {
		log.info("welcome to slf4j annotation. LOG_CLASS={}", log.getClass().getName());
	}
}