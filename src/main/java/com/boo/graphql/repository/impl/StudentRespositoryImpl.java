package com.boo.graphql.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.boo.graphql.entities.Student;
import com.boo.graphql.repository.StudentRepository;

@Controller
public class StudentRespositoryImpl {

	@Autowired
	private StudentRepository studentRepository;
	
	@SchemaMapping(typeName = "Mutation", value = "createStudent")
	public Student createStudent(@Argument Student student) {
		return studentRepository.save(student);
	}
	
	@SchemaMapping(typeName = "Query", value = "findAllStudents")
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}
	
	@SchemaMapping(typeName = "Query", value = "findStudentById")
	public Student findStudentById(@Argument String id) {
		return studentRepository.findById(id).orElse(null);
	}
	
	public Student resolveStudentReference(Map<String, Object> reference) {
        if (reference.get("id") instanceof String fooId) {
            return findStudentById(fooId);
        } else {
            return null;
        }
    }
}
