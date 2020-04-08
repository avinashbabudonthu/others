package hello.world.repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hello.world.model.Student;

public class StudentRepository {

	private static List<Student> studentList = new ArrayList<>();

	static {
		studentList.add(Student.builder().name("jim").age(22).build());
		studentList.add(Student.builder().name("jill").age(20).build());
		studentList.add(Student.builder().name("jack").age(13).build());
		studentList.add(Student.builder().name("john").age(14).build());
		studentList.add(Student.builder().age(14).build());

		Date date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		studentList.add(Student.builder().name("jane").age(14).joiningDate(date).build());

		Date date2 = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		studentList.add(Student.builder().name("smith").age(14).joiningDate(date2).build());
	}

	public List<Student> findAllStudents() {
		return studentList;
	}
}
