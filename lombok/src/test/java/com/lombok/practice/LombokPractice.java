package com.lombok.practice;

import java.io.InputStream;

import org.junit.Test;

import com.lombok.practice.Student9.Student9Builder;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.Synchronized;

public class LombokPractice {

	@Test
	public void dataAnnotation() {
		Student student = new Student();
		student.setFirstName("jack");
		student.setCourseId(123456L);
		student.setAge(21);

		System.out.println("student=" + student);
		System.out.println("student.firstName=" + student.getFirstName());
	}

	@Test
	public void getterSetterAnnotations() {
		Student2 student = new Student2();
		student.setFirstName("jack");
		student.setCourseId(123456L);
		student.setAge(21);

		System.out.println("student=" + student);
		System.out.println("student.firstName=" + student.getFirstName());
	}

	@Test
	public void getterSetterToStringAnnotations() {
		Student3 student = new Student3();
		student.setFirstName("jack");
		student.setCourseId(123456L);
		student.setAge(21);

		System.out.println("student=" + student);
		System.out.println("student.firstName=" + student.getFirstName());
	}

	@Test
	public void allArgsConstructorAnnotations() {
		Student4 student = new Student4("jack", 123456L, 21);

		System.out.println("student=" + student);
		System.out.println("student.firstName=" + student.getFirstName());
	}

	@Test
	public void noArgsAndAllArgsConstructorAnnotations() {
		Student5 student1 = new Student5();
		student1.setFirstName("jack");
		student1.setCourseId(123456L);
		student1.setAge(21);

		System.out.println("student1=" + student1);
		System.out.println("student1.firstName=" + student1.getFirstName());

		Student5 student2 = new Student5("jack", 123456L, 21);
		System.out.println("student2=" + student2);
		System.out.println("student2.firstName=" + student2.getFirstName());
	}

	@Test
	public void requiredArgsConstructorAnnotations() {
		Student6 student = new Student6("jack", 123456L);
		student.setAge(21);

		System.out.println("student=" + student);
		System.out.println("student.firstName=" + student.getFirstName());
	}

	@Test
	public void accessorsAnnotation() {
		Student7 student = new Student7().firstName("jack").courseId(123456L).age(21);
		System.out.println("student=" + student);
		System.out.println("student.firstName=" + student.firstName());
	}

	@Test
	public void equalsAndHashCodeAnnotation() {
		Student8 student = new Student8("jack", 123456L, 21);
		Student8 student2 = new Student8("jack", 123456L, 21);
		Student8 student3 = new Student8("jill", 123457L, 22);

		System.out.println(student.hashCode());
		System.out.println(student2.hashCode());
		System.out.println(student3.hashCode());

		System.out.println(student.equals(student2));
		System.out.println(student2.equals(student3));
	}

	@Test
	public void builderAnnotation() {
		Student9 student = new Student9Builder().firstName("jack").courseId(123456L).age(21).build();
		System.out.println("student=" + student);
		System.out.println("student.firstName=" + student.getFirstName());

		// method 2
		Student9 student2 = Student9.builder().firstName("jack").courseId(123456L).age(21).build();
		System.out.println("student2=" + student2);
		System.out.println("student2.firstName=" + student2.getFirstName());
	}

	@Test
	public void nonNullAnnotation() {
		try {
			Student10 student = new Student10(null, null, null);
			System.out.println("student=" + student);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @SneakyThrow will avoid javac's insistence that you either catch or throw onward any checked exceptions that statements in your method body declare they generate. 
	 * @SneakyThrow does not silently swallow, wrap into RuntimeException, or otherwise modify any exceptions of the listed checked exception types. 
	 * The JVM does not check for the consistency of the checked exception system; javac does, and this annotation lets you opt out of its mechanism
	 */
	@SneakyThrows
	public void method2() {
		new LombokModel().method1();
	}

	/**
	 * @Cleanup works similar to try-with-resource
	 */
	@Test
	@SneakyThrows
	public void cleanUpAnnotation() {
		@Cleanup
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("file1.txt");
	}

	/**
	 * In Java you can use the synchronized keyword to implement critical sections. 
	 * However, this is not a 100% safe approach: other client code can eventually also synchronize on your instance, potentially leading to unexpected deadlocks.
	 * This is where @Synchronized comes in: annotate your methods (both instance and static) with it and you’ll get an auto generated private, un exposed field your implementation will use for locking
	 */
	@Synchronized
	public void synchronizedNonStaticMethod() {
//		$LOCK.notifyAll();
	}

	/**
	 * In Java you can use the synchronized keyword to implement critical sections. 
	 * However, this is not a 100% safe approach: other client code can eventually also synchronize on your instance, potentially leading to unexpected deadlocks.
	 * This is where @Synchronized comes in: annotate your methods (both instance and static) with it and you’ll get an auto generated private, un exposed field your implementation will use for locking
	 */
	@Synchronized
	public static void synchronizedStaticMethod() {

	}
}