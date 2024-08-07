package projects.springneo4jtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.springneo4jtest.Service.PersonService;
import projects.springneo4jtest.common.ResponseMessage;
import projects.springneo4jtest.model.Person;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public Person createPerson(@RequestParam String name, @RequestParam int age, @RequestParam String email) {
        return personService.createPerson(name, age, email);
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getAllPersons() {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .content(personService.getAllPersons())
                .http(new ResponseMessage.Status(HttpStatus.OK))
                .build();
        return new ResponseEntity<>(responseMessage, responseMessage.getHttp().getStatus());
//        return personService.getAllPersons();
    }

    @GetMapping("/{name}")
    public Optional<Person> getPersonByName(@PathVariable String name) {
        return personService.getPersonByName(name);
    }

    @DeleteMapping
    public void deleteAllPersons() {
        personService.deleteAllPersons();
    }

}
