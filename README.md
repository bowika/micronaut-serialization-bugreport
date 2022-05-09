## Test case to show micronaut serialization failure

Given this Parent
```
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = FirstChild.class, name = "FirstChild"),
    @JsonSubTypes.Type(value = SecondChild.class, name = "SecondChild")
})
@Serdeable
public class Parent {
```
Having 2 child classes
```
@Serdeable
public class FirstChild extends Parent {
    private boolean fieldOfFirst;
```
and
```
@Serdeable
public class SecondChild extends Parent {
    private int fieldOfSecond;
    private Parent sibling;
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

Note that marking Parent as abstract makes it even worse since it tries to instantiate it.