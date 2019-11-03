package com.lombok.practice;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class Student9 {
	private String firstName;
	private Long courseId;
	private Integer age;
}