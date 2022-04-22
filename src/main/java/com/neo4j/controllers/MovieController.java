package com.neo4j.controllers;

import com.neo4j.entities.Movie;
import com.neo4j.entities.Person;
import com.neo4j.repostiories.MovieRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PutMapping
    ResponseEntity<Void> createOrUpdateMovie(@RequestBody Movie newMovie) {
        movieRepository.save(newMovie);
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Flux<Movie>> getMovies() {
        return ResponseEntity.ok(movieRepository.findAll());
    }

    @ResponseBody
    @GetMapping(value = "/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Mono<Movie>> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(movieRepository.findOneByTitle(title));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Mono<Void>> delete(@PathVariable String id) {
        movieRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @GetMapping(value = "{title}/directors", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Person>> getDirectors(@PathVariable String title) {
        return ResponseEntity.ok(movieRepository.findOneByTitle(title).block().getDirectors());
    }
}