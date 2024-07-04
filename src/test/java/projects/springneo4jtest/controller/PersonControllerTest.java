package projects.springneo4jtest.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import projects.springneo4jtest.common.neo4jtest.Neo4jWebTest;
import projects.springneo4jtest.Service.PersonService;
import projects.springneo4jtest.model.Person;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class PersonControllerTest {

    @Autowired PersonService personService;

    protected static MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(
                        documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withRequestDefaults(prettyPrint())
                                .withResponseDefaults(prettyPrint())
                )
                .build();
    }

    private static Neo4j newServer;

    @BeforeAll
    static void initializeNeo4j() {
        newServer = Neo4jBuilders.newInProcessBuilder()
                .withDisabledServer()
//                .withFixture("CREATE (b:Book {isbn: '978-0547928210', name: 'The Fellowship of the Ring', year: 1954})" +
//                        "-[:WRITTEN_BY]->(a:Author {id: 1, name: 'J. R. R. Tolkien'})" +
//                        "CREATE (b2:Book {isbn: '978-0547928203', name: 'The Two Towers', year: 1956})-[:WRITTEN_BY]->(a)")
                .build();
    }
    @AfterAll
    static void stopNeo4j() {
        newServer.close();
    }

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", newServer::boltURI);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> "null");
    }

    @Test
    @DisplayName("모든 사람 찾기")
    void getAllPersonTest() throws Exception {
        //given
        Person dummyPerson = personService.createPerson("dummyPerson", 55, "dummyPerson@example.com");

        //when
        ResultActions perform = mockMvc
                .perform(get("/persons"))
                .andDo(print());

        //then
        ResultActions success = perform.andExpect(status().isOk());

        //docs
//        success.andDo(document("Person"));
        success.andDo(document("Person", responseFields(
                fieldWithPath("content").description("test")
        )));
    }


}