package projects.springneo4jtest.common.neo4jtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
public class Neo4jBaseTest {

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

}
