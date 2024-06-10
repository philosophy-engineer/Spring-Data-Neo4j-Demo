package projects.springneo4jtest.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    @Test
    @DisplayName("Person Unit test")
    void createPerson() throws Exception {
        //given
        Person dummyPerson = new Person("dummyPerson", 26, "dummyPerson@example.com");
        //when

        //then
        assertThat(dummyPerson.getClass()).isEqualTo(Person.class);
        assertThat(dummyPerson.getAge()).isBetween(10, 100);
    }

}