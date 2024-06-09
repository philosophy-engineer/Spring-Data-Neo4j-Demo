package projects.springneo4jtest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Person {

    @Id
    private String name;
    private int age;
    private String email;

}
