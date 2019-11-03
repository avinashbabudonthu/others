package com.lombok.practice;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Student7 {
	private String firstName;
	private Long courseId;
	private Integer age;
}