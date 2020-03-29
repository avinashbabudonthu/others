package hello.world.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private String name;
	private Integer age;

	public boolean isAgeGreaterThan21() {
		return age > 21;
	}

	public boolean isAgeLessThan15() {
		return age < 15;
	}
}
