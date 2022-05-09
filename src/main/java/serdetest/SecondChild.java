package serdetest;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class SecondChild extends Parent {
    private int fieldOfSecond;
    private Parent sibling;

    public int getFieldOfSecond() {
        return fieldOfSecond;
    }

    public SecondChild setFieldOfSecond(int fieldOfSecond) {
        this.fieldOfSecond = fieldOfSecond;
        return this;
    }

    public Parent getSibling() {
        return sibling;
    }

    public SecondChild setSibling(Parent sibling) {
        this.sibling = sibling;
        return this;
    }

    @Override
    public String toString() {
        return "SecondChild{" +
            "name='" + name + '\'' +
            ", fieldOfSecond=" + fieldOfSecond +
            ", sibling=" + sibling +
            '}';
    }
}
