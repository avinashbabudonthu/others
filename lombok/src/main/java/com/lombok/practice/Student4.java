package com.lombok.practice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Student4 {
	private String firstName;
	private Long courseId;
	private Integer age;
}