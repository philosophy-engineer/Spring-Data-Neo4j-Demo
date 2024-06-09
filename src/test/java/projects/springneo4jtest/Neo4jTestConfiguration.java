package projects.springneo4jtest;

import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jTestConfiguration {

    @Bean(destroyMethod = "close")
    public Neo4j embeddedNeo4j() {
        return Neo4jBuilders.newInProcessBuilder().withDisabledServer().build();
    }

    @Bean(destroyMethod = "close")
    public Driver neo4jDriver(Neo4j embeddedNeo4j) {
        return GraphDatabase.driver(embeddedNeo4j.boltURI(), AuthTokens.none());
    }
}

