package com.lombok.practice;

import lombok.Data;
import lombok.NonNull;

@Data
public class Student10 {
	private @NonNull String firstName;
	private @NonNull Long courseId;
	private @NonNull Integer age;
}