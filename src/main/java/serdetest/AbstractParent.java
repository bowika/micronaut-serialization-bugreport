package serdetest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.micronaut.serde.annotation.Serdeable;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = FirstChild.class, name = "FirstChild"),
    @JsonSubTypes.Type(value = SecondChild.class, name = "SecondChild")
})
@Serdeable
public class AbstractParent {
    protected String name;

    public String getName() {
        return name;
    }

    public AbstractParent setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "AbstractParent{" +
            "name='" + name + '\'' +
            '}';
    }
}
