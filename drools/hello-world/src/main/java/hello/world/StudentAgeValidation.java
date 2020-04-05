package hello.world;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import hello.world.model.Student;
import hello.world.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentAgeValidation {

	private static StudentRepository studentRepository = new StudentRepository();

	public static void main(String[] args) {
		log.info("Running Student age validation rules");
		List<Student> studentList = studentRepository.findAllStudents();

		KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
		StatelessKieSession statelessKieSession = kieContainer.newStatelessKieSession("StudentAgeValidationRule1");

		statelessKieSession.setGlobal("studentAgeValidation", new StudentAgeValidation());

		log.info("drools engine started");
		statelessKieSession.execute(studentList);
		log.info("drools engine completed");
	}

	public void printMessage(String... messages) {
		String str = String.join(", ", messages);
		System.out.println(str);
	}
}
