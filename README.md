# Basketball Team Registration API Wİth Spring Boot and GraphQL 

This is a Spring Boot project for basketball team registration by using GraphQL.

### REQUIREMENTS
* Java 8^
* Maven
* H2 DB (Comes as embedded with Spring)
* Git

## RUN THE PROJECT

- Clone the project
- Build the project
- Run the Project
```
maven clean
maven install
```

### USAGE
- You can use the application with custom requests using Playground 
```
http://localhost:8080/playground
```
- Get all players:
```
{
  findAllPlayers {
    id
    name
    surname
    position {
      id
      type
    }
  }
}
```

- New player:
```
mutation {
  newPlayer(
    name: "cagri2",
    surname: "zeki2",
    position: "C"){id name surname position{id type}}
}
```

- Delete player:
```
mutation{
  deletePlayer(id:7)
}
```