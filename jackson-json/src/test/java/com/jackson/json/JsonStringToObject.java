package com.jackson.json;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.json.model.Employee;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonStringToObject {

	/**
	 * Input Json: [{"name": "jim", "value": {"id": "1", "name": "jim"}}, {"name": "jill", "value": {"id": "2", "name": "jill"}}]
	 * 
	 * Output: List<Map<String, Employee>>
	 */
	@SneakyThrows
	@Test
	public void jsonStringToMapOfStringAndObject() {
		final String jsonString = "[{\"emp1\": {\"id\": \"1\", \"name\": \"jim\"}}, {\"emp2\": {\"id\": \"2\", \"name\": \"jill\"}}]";
		List<Map<String, Employee>> employeesMap = new ObjectMapper().readValue(jsonString,
				new TypeReference<List<Map<String, Employee>>>() {
				});

		// input-json=[{"emp1": {"id": "1", "name": "jim"}}, {"emp2": {"id": "2", "name": "jill"}}]
		log.info("input-json={}", jsonString);

		// employees-map=[{emp1=Employee(id=1, name=jim)}, {emp2=Employee(id=2, name=jill)}]
		log.info("employees-map={}", employeesMap);
	}
}