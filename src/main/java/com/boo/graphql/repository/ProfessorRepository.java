package com.boo.graphql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boo.graphql.entities.Professor;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
	
}
