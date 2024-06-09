package projects.springneo4jtest.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import projects.springneo4jtest.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, String> {

    Optional<Person> findByName(String name);

}
