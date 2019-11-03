package com.lombok.practice;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Generates a constructor with required arguments. 
 * Required arguments are final fields and fields with constraints such as @NonNull
 * @author Admin
 *
 */
@RequiredArgsConstructor
@Getter
public class Student6 {
	private final @NonNull String firstName;
	private final @NonNull Long courseId;
	private @Setter Integer age;
}