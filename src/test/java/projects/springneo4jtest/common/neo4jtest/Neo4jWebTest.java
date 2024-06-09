package projects.springneo4jtest.common.neo4jtest;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import projects.springneo4jtest.common.neo4jtest.Neo4jBaseTest;

@SpringBootTest
public class Neo4jWebTest extends Neo4jBaseTest {

    protected static MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .build();
    }

}
