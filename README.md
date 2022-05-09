## Test case to show micronaut serialization failure

Given this AbstractParent
```
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = FirstChild.class, name = "FirstChild"),
    @JsonSubTypes.Type(value = SecondChild.class, name = "SecondChild")
})
public class AbstractParent
```
Having 2 child classes
```
public class FirstChild extends AbstractParent {
    private boolean fieldOfFirst;
```
and
```
public class SecondChild extends AbstractParent {
    private int fieldOfSecond;
    private AbstractParent sibling;
```
Then on serializing:
```
FirstChild first = (FirstChild) new FirstChild().setFieldOfFirst(true).setName("first");
SecondChild secondChild = (SecondChild) new SecondChild().setFieldOfSecond(1).setSibling(first).setName("second");
String json = mapper.writeValueAsString(secondChild);
```
Gives:
```
{"type":"SecondChild","fieldOfSecond":1,"sibling":{"name":"first"},"name":"second"}
```
Instead of:
```
{"type":"SecondChild","fieldOfSecond":1,"sibling":{"name":"first", "type": "FirstChild", "fieldOfFirst": true},"name":"second"}
```

Failing test case is created under tests:
SerdetestTest::serializeDeserialize_withSibling

[Github repo](https://github.com/bowika/micronaut-serialization-bugreport)
