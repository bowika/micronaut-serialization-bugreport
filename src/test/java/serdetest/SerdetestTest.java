package serdetest;

import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@MicronautTest
class SerdetestTest {
    @Inject
    ObjectMapper mapper;

    @Test
    void serializeDeserialize_simpleHappyPath() throws IOException {
        FirstChild firstChild = (FirstChild) new FirstChild().setFieldOfFirst(true).setName("first");
        String json = mapper.writeValueAsString(firstChild);
        FirstChild actual = mapper.readValue(json, FirstChild.class);
        Assertions.assertTrue(actual.isFieldOfFirst());
    }

    @Test
    void deserialize_withSiblingHappyPath() throws IOException {
        String json = "{\"type\":\"SecondChild\",\"fieldOfSecond\":1,\"sibling\":{\"name\":\"first\", \"type\": \"FirstChild\", \"fieldOfFirst\": true},\"name\":\"second\"}";
        AbstractParent actual = mapper.readValue(json, AbstractParent.class);
        Assertions.assertInstanceOf(SecondChild.class, actual);
        //sibling has to be FirstChild
        Assertions.assertInstanceOf(FirstChild.class, ((SecondChild)actual).getSibling());
        FirstChild sibling = (FirstChild) ((SecondChild)actual).getSibling();
        //note that by default it is false
        Assertions.assertTrue(sibling.isFieldOfFirst());
    }

    @Test
    void serializeDeserialize_withSibling() throws IOException {
        FirstChild first = (FirstChild) new FirstChild().setFieldOfFirst(true).setName("first");
        SecondChild secondChild = (SecondChild) new SecondChild().setFieldOfSecond(1).setSibling(first).setName("second");
        String json = mapper.writeValueAsString(secondChild);

        //to make sure that the assertions are right please comment in this line
//        json = "{\"type\":\"SecondChild\",\"fieldOfSecond\":1,\"sibling\":{\"name\":\"first\", \"type\": \"FirstChild\", \"fieldOfFirst\": true},\"name\":\"second\"}";

        System.out.println(secondChild); //toString
        System.out.println(json); //bad deserialization
        AbstractParent actual = mapper.readValue(json, AbstractParent.class);
        System.out.println(actual); //toString
        //outer has to be SecondChild
        Assertions.assertInstanceOf(SecondChild.class, actual);
        //sibling has to be FirstChild
        Assertions.assertInstanceOf(FirstChild.class, ((SecondChild)actual).getSibling());
        FirstChild sibling = (FirstChild) ((SecondChild)actual).getSibling();
        //note that by default it was false
        Assertions.assertTrue(sibling.isFieldOfFirst());
    }
}
