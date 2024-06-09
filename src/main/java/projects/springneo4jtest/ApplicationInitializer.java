package projects.springneo4jtest;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import projects.springneo4jtest.model.Person;
import projects.springneo4jtest.repository.PersonRepository;

@Component
@RequiredArgsConstructor
public class ApplicationInitializer {

    private final PersonRepository personRepository;

    @Bean
    CommandLineRunner initializeDatabase() {
        return args -> {
            // Clear the database
            personRepository.deleteAll();

            // Add dummy data
            personRepository.save(new Person("Alice", 30, "alice@example.com"));
            personRepository.save(new Person("Bob", 25, "bob@example.com"));
            personRepository.save(new Person("Carol", 27, "carol@example.com"));
            personRepository.save(new Person("Dave", 35, "dave@example.com"));
            personRepository.save(new Person("Eve", 22, "eve@example.com"));

            System.out.println("Database initialized with dummy data.");
        };
    }

}
