# elevator-services

## Design
Spring Boot is used to develop this simple elevator monitoring system. RESTful webservices are used to integrate with multiple interfaces easily. WebSocket is used to push elevator movement, so it can be moniroted with refreshing the client. This sytem is developed using open source technologies, so no licensing cost invloved.

### Prerequisites
- Java 8
- Spring Boot
- WebSocket
- RESTful Web Service

### Running the Backend
- mvn clean package, and deploy to tomcat.

### Accessing Application
- index.html is a entry point to view the status of elevators.
- There are two rest method
  -@GetMapping("/elevator/{id}/{floor}")
    -This method is used to call up, down, or move lift to particular floor between 1 to 6
  -@GetMapping("/elevator/stop/{id}")
    -This method is used to stop elevator, that prevents further movement of lift
