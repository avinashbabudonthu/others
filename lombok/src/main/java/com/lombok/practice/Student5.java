package com.lombok.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student5 {
	private String firstName;
	private Long courseId;
	private Integer age;
}