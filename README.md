
# FourthCinema

FourthCinema is a sample project of an cinema to show some of mine skill programming

## How to run

The project was build using gradle, so the simplest way to run is executing the follow command:

    ./gradlew run

**Note:** Is mandatory set the environment variable *OMDB_API_KEY*  to start the application.
**Note 2:** To make easy, the application starts with all movies in database and the id is the sequence of movies. You can also check the script in resources folder on repository module.

To access the swagger, the Url is
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Database
I chose an relational database more about my confort, I had worked before with HsqlDB, so I chose this database to support me in this project.
The image below represents my ERD:
![FourthCinema ERD](https://github.com/rdelsole/fourthcinema/blob/main/img/erd.jpg?raw=true)

**Note:** In a real world, the cinema has prices different by week days  and hours of day, to keep it simple I just organized the prices by hours and rooms and I also implemented a Room entity with only one characteristic of room as capacity, but here could have others attributes of room as sound system, for example.

## Architecture

To develop this project I chose the clean architecture approach, splitting my layers in modules, using this picture as an example:
![enter image description here](https://github.com/rdelsole/fourthcinema/blob/main/img/Clean-Architecture-graph.png?raw=true)
The responsibility of each module are:
* Web - To deal with Rest interface, input data validation, conversion of input data to system data and output validation and deserialization.
* Repository - Responsible to deal with Database queries and conversions of data.
* Omdb-service - Responsible to do the interface with this partner, here I could use a cache or implementing fall back strategies if necessary when this service is down. Other behavior that I have implemented here was to cut just a small part of omdb api, just for get time to not be necessary copy all attributes in each layer.
* Usecase - Core of system, I chose to have only class with one responsibility at this layer, I prefer this approach to be easier to change a behavior when it is necessary
* Entity - The model of application, should be agnostic of repósitory or web layer, each layer need to know how to convert their models to entity models.
* Application - Is the glue of all layers, allowing the spring make it magic

## Technologies
As main framework I chose Spring boot and their modules, like spring-jpa.
In omdb-service I chose the fuel http client, I like their approach and I know that is more like a functional way to deal with http return.
And to help to keep the code in a pattern I chose the ktlint plugin.

## Testing
I got the Mockk as my mock framework and JUnit 5 to run my tests.

I implemented one test in each layer, mocking the others layers to isolate the test, and implemented one Integration test in application layer.
**Note:** I don't implemented a test in omdb-service, but I could do this using wiremock to mock the http request with my deserved return.
