package com.boo.graphql.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.boo.graphql.entities.Professor;
import com.boo.graphql.repository.ProfessorRepository;

@Controller
public class ProfessorRepositoryImpl {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@SchemaMapping(typeName = "Mutation", value = "createProfessor")
	public Professor createProfessor(@Argument Professor professor) {
		return professorRepository.save(professor);
	}
	
	@SchemaMapping(typeName = "Query", value = "findAllProfessors")
	public List<Professor> findAllProfessors() {
		return professorRepository.findAll();
	}
	
	@SchemaMapping(typeName = "Query", value = "findProfessorById")
	public Professor findProfessorById(@Argument String id) {
		return professorRepository.findById(id).orElse(null);
	}
	
	public Professor resolveProfessorReference(Map<String, Object> reference) {
        if (reference.get("id") instanceof String fooId) {
            return findProfessorById(fooId);
        } else {
            return null;
        }
    }
}
