---
runme:
  id: 01HWD2QNCMPQYHGCVJ0RDE537G
  version: v3
---

# CursoSpringboot-BuildRestAPI

When you create a project using Spring Initializr, you're asked to provide some basic information about your project. Here's what each of the terms means:

1. Group: The group is a unique identifier for your project. It often follows the reverse domain name structure of the company or developer as a convention. For example, if your domain is "mycompany.com", you might use "com.mycompany" as the group. This helps ensure that the Java package name is unique and doesn't conflict with other packages.
2. Artifact: The artifact is the unique name of your project. This name will be used to generate the final JAR or WAR file. It can be any name you like, but it's recommended to be descriptive and represent the purpose or functionality of your project.
3. Name: This is the human-readable name for your project. It's the name others will see when they look at your project. Again, it's recommended to be descriptive and represent the purpose of the project.
4. Description: The description is a brief explanation of what your project does. It provides details about the main functionality or purpose of the project. This can help other developers quickly understand what your project is about when they see it on Spring Initializr or in a code repository.

Test classes in a standard Java project are in the src/test directory. In this particular case is in src/test/java/example/cashcard directory.
Code is included in src/main. New clases/ objects are inserted here.

Classes vs Records

Use a class when you need a more complex data structure with custom behavior, inheritance, and/or mutability.

```java {"id":"01HWD2QNCMPQYHGCVJ0QC8TB07"}
public class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getters and setters
    // Other methods
}
```

Use a record when you need a simple and immutable data structure to represent data. Records are particularly useful when working with data transfer between systems or storing simple data in your program.

```sql {"id":"01HWD2QNCMPQYHGCVJ0R07MQ3D"}
public record Person(String name, int age) {}
```

Practice Test Driven Development (tests before implementing the application code):

1. test-first approach (myFirstTest in CashCardJsonTest)
2. test-first development (cashCardSerializationTest and cashCardDeserializationTest in CashCardJsonTest)

REST = Representational State Transfer
CRUD Operations:

1. Create: POST
2. Read: GET
3. Update:
   3.1. PUT: create or replace the complete record
   3.2. PATCH: update the partial record. Used when records are large.
4. Delete: DELETE

| HTTP Method | Operation | Definition of Resource URI | What does it do? | Response Status Code | Response Body |
|-------------|-----------|----------------------------|-------------------|----------------------|---------------|
| POST        | Create    | Server generates and returns the URI | Creates a sub-resource ("under" or "within" the passed URI) | 201 CREATED | The created resource |
| PUT         | Create    | Client supplies the URI     | Creates a resource (at the Request URI) | 201 CREATED | The created resource |
| PUT         | Update    | Client supplies the URI     | Replaces the resource: The entire record is replaced by the object in the Request | 204 NO CONTENT | (empty) |
| PATCH       | Update    | Client supplies the URI     | Partial Update: modify only fields included in the request on the existing record | 200 OK | The updated resource |

Testing END POINTS

GET (getForEntity)
```
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .getForEntity("/cashcards/102", String.class); 
```
POST (postForEntity)
```
        ResponseEntity<Void> createResponse = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .postForEntity("/cashcards", newCashCard, Void.class);
```
PUT (exchange)
```
        ResponseEntity<Void> response = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .exchange("/cashcards/99", HttpMethod.PUT, request, Void.class);
```
