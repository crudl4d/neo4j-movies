package com.neo4j.repostiories;

import com.neo4j.entities.Person;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface PersonRepository extends ReactiveNeo4jRepository<Person, String> {
    Mono<Person> findOneByName(String name);
}
