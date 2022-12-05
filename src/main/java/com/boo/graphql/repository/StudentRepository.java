package com.boo.graphql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.boo.graphql.entities.Student;

@Service
public interface StudentRepository extends MongoRepository<Student, String> {
	
}
