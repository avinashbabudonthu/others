package com.lombok.practice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student3 {
	private String firstName;
	private Long courseId;
	private Integer age;
}