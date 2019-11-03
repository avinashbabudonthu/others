package com.lombok.practice;

import java.util.Date;

import lombok.Value;

@Value
public class Student11 {

	private String firstName;
	private Date joiningDate;
	private Double grade;

	@Value(staticConstructor = "of")
	public static class Address {
		private String streetName;
		private Long houseNumber;
	}
}
