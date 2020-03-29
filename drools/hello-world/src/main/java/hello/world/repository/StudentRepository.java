package hello.world.repository;

import java.util.ArrayList;
import java.util.List;

import hello.world.model.Student;

public class StudentRepository {

	private static List<Student> studentList = new ArrayList<>();

	static {
		studentList.add(Student.builder().name("jim").age(22).build());
		studentList.add(Student.builder().name("jill").age(20).build());
		studentList.add(Student.builder().name("jack").age(13).build());
		studentList.add(Student.builder().name("john").age(14).build());
	}

	public List<Student> findAllStudents() {
		return studentList;
	}
}
