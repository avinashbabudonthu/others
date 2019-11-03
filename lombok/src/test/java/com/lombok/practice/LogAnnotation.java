package com.lombok.practice;

import org.junit.Test;

import lombok.extern.java.Log;

/**
 * Refer https://projectlombok.org/features/log
 * @author Avinash Babu Donthu
 *
 */
@Log
public class LogAnnotation {

	@Test
	public void method1() {
		log.info("welcome to Log annotation: " + log.getClass().getName());
	}
}
