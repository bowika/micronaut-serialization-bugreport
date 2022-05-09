package serdetest;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class SecondChild extends AbstractParent {
    private int fieldOfSecond;
    private AbstractParent sibling;

    public int getFieldOfSecond() {
        return fieldOfSecond;
    }

    public SecondChild setFieldOfSecond(int fieldOfSecond) {
        this.fieldOfSecond = fieldOfSecond;
        return this;
    }

    public AbstractParent getSibling() {
        return sibling;
    }

    public SecondChild setSibling(AbstractParent sibling) {
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
