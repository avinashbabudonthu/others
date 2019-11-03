package com.lombok.practice;

import java.util.Date;

import org.junit.Test;

import com.lombok.practice.Student11.Address;

import lombok.extern.slf4j.Slf4j;

/**
 * Refer https://www.projectlombok.org/features/Value
 * @author Avinash Babu Donthu
 *
 */
@Slf4j
public class ValueAnnotation {

	@Test
	public void method1() {
		Student11 student = new Student11("jack", new Date(), 3.45);
		Address address = Address.of("streetName1", 1234L);

		log.info("student={}, Address={}", student, address);
	}
}
