package projects.springneo4jtest.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.springneo4jtest.model.Person;
import projects.springneo4jtest.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person createPerson(String name, int age, String email) {
        Person person = new Person(name, age, email);
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonByName(String name) {
        return personRepository.findByName(name);
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }

    public boolean deletePersonByName(String name) {
        Optional<Person> person = personRepository.findByName(name);
        if (person.isPresent()) {
            personRepository.delete(person.get());
            return true;
        }
        return false;
    }

}
