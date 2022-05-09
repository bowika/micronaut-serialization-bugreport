package serdetest;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class FirstChild extends AbstractParent {
    private boolean fieldOfFirst;

    public boolean isFieldOfFirst() {
        return fieldOfFirst;
    }

    public FirstChild setFieldOfFirst(boolean fieldOfFirst) {
        this.fieldOfFirst = fieldOfFirst;
        return this;
    }

    @Override
    public String toString() {
        return "FirstChild{" +
            "name='" + name + '\'' +
            ", fieldOfFirst=" + fieldOfFirst +
            '}';
    }
}
