package projects.springneo4jtest.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import projects.springneo4jtest.Neo4jTestConfiguration;
import projects.springneo4jtest.model.Person;
import projects.springneo4jtest.repository.PersonRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(value = Neo4jTestConfiguration.class)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Driver driver;

    @BeforeEach
    void setUp() {
        try (Session session = driver.session()) {
            session.run("MATCH (n) DETACH DELETE n");
        }
    }

    @Test
    public void testCreatePerson() {
        Person person = new Person("Alice", 30, "alice@example.com");
        Person createdPerson = personService.createPerson("Alice", 30, "alice@example.com");

        assertNotNull(createdPerson);
        assertEquals(person.getName(), createdPerson.getName());
        assertEquals(person.getAge(), createdPerson.getAge());
        assertEquals(person.getEmail(), createdPerson.getEmail());
    }

    @Test
    @DisplayName("이름으로 사람 찾기")
    public void testGetPersonByName() {
        Person person = new Person("Alice", 30, "alice@example.com");
        personRepository.save(person);

        Optional<Person> foundPerson = personService.getPersonByName("Alice");

        assertTrue(foundPerson.isPresent());
        assertEquals(person.getName(), foundPerson.get().getName());
        assertEquals(person.getAge(), foundPerson.get().getAge());
        assertEquals(person.getEmail(), foundPerson.get().getEmail());
    }

    @Test
    public void testDeletePersonByName() {
        Person person = new Person("Alice", 30, "alice@example.com");
        personRepository.save(person);

        boolean isDeleted = personService.deletePersonByName("Alice");

        assertTrue(isDeleted);
        Optional<Person> deletedPerson = personRepository.findByName("Alice");
        assertFalse(deletedPerson.isPresent());
    }
}
