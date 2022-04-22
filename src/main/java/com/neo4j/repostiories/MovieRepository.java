package com.neo4j.repostiories;

import com.neo4j.entities.Movie;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface MovieRepository extends ReactiveNeo4jRepository<Movie, String> {
    Mono<Movie> findOneByTitle(String title);
}
