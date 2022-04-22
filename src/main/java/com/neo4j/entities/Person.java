package com.neo4j.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node("Person")
@Getter
@Setter
public class Person {
    @Id
    private final String name;
    private final Integer born;
    @Relationship(type = "ACTED_IN", direction = OUTGOING)
    private Set<Person> moviesActed = new HashSet<>();
    @Relationship(type = "DIRECTED", direction = OUTGOING)
    private Set<Person> moviesDirected = new HashSet<>();
    public Person(Integer born, String name) {
        this.born = born;
        this.name = name;
    }
    //Getters omitted
}