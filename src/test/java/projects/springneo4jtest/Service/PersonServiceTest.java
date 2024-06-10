package projects.springneo4jtest.Service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projects.springneo4jtest.common.neo4jtest.Neo4jBaseTest;
import projects.springneo4jtest.model.Person;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonServiceTest extends Neo4jBaseTest {

    @Autowired 
    private PersonService personService;
    
    @Test
    @DisplayName("Test name1")
    void methodName() throws Exception {
        //given
        personService.createPerson("dummyPerson", 18, "dummyPerson@na");

        //when
        List<Person> allPersons = personService.getAllPersons();

        for (Person p: allPersons){
            System.out.println("p = " + p.getName() + " " + p.getAge() + " " + p.getEmail());
        }
        //then
        Assertions.assertEquals(allPersons.size(), 6);

    }

    @Test
    @DisplayName("Test name2")
    void methodName2() throws Exception {
        //given
        personService.createPerson("dummyPerson", 22, "dummyPerson@na");

        //when
        List<Person> allPersons = personService.getAllPersons();

        for (Person p: allPersons){
            System.out.println("p = " + p.getName() + " " + p.getAge() + " " + p.getEmail());
        }
        //then
        assertThat(allPersons.size()).isEqualTo(3);

    }
}
