package projects.springneo4jtest.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import projects.springneo4jtest.common.neo4jtest.Neo4jWebTest;
import projects.springneo4jtest.Service.PersonService;
import projects.springneo4jtest.model.Person;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PersonControllerTest extends Neo4jWebTest {

    @Autowired PersonService personService;

    @Test
    @DisplayName("모든 사람 찾기")
    void getAllPersonTest() throws Exception {
        //given
        Person dummyPerson = personService.createPerson("dummyPerson", 55, "dummyPerson@example.com");

        //when
        ResultActions perform = Neo4jWebTest.mockMvc.perform(get("/persons"));

        //then
        perform.andExpect(status().isOk());
        perform.andExpect(status().isNotFound());
    }


}