package com.boo.graphql;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.apollographql.federation.graphqljava.tracing.FederatedTracingInstrumentation;
import com.boo.graphql.entities.Professor;
import com.boo.graphql.entities.Student;
import com.boo.graphql.repository.impl.ProfessorRepositoryImpl;
import com.boo.graphql.repository.impl.StudentRespositoryImpl;

@Configuration
public class GraphQLConfiguration {
	
	@Autowired
	StudentRespositoryImpl studentRespositoryImpl;
	
	@Autowired
	ProfessorRepositoryImpl professorRespositoryImpl;

	@Bean
	public FederatedTracingInstrumentation federatedTracingInstrumentation() {
		return new FederatedTracingInstrumentation();
	}

	@Bean
	public GraphQlSourceBuilderCustomizer federationTransform() {
		return builder -> builder.schemaFactory((registry, wiring) -> 
				Federation.transform(registry, wiring)
					.fetchEntities(env -> 
						env.<List<Map<String, Object>>>getArgument(_Entity.argumentName).stream().map(reference -> {
							final String typeName = (String) reference.get("__typename");
							return switch (typeName) {
								case "Student" -> studentRespositoryImpl.resolveStudentReference(reference);
								case "Professor" -> professorRespositoryImpl.resolveProfessorReference(reference);
								default -> null;
							};
						}).collect(Collectors.toList())).resolveEntityType(env -> {
							final Object src = env.getObject();
							if (src instanceof Student) {
								return env.getSchema().getObjectType("Student");
							} else if(src instanceof Professor){
								return env.getSchema().getObjectType("Professor");
							} else {
								return null;
							}
						}).build());
	}

}
