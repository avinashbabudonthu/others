package com.lombok.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Student8 {
	private String firstName;
	private Long courseId;
	private Integer age;
}