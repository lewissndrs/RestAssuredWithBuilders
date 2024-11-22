## Background
This repo is a demo to build an advanced REST Assured framework that meets the following problem statement:

As API test automation frameworks grow, they have a habit of becoming hard to maintain. We will often start with some very simple, clear tests where the request data is defined in the test itself and we can assert whatever we need from the response very easily. 
However, as we start to add more complex tests we will start abstracting that data out to either:

1. Fixtures such as JSON files, and then we will need new fixtures for each test case. This gets very messy to maintain.
2. POJO objects which are initialised with data and then serialised to JSON using libraries like Jackson. POJOS are often quite verbose due to constructors, getters and setters and initialising them can be messy if they have large numbers of arguments. If these objects are being initialised within tests then the tests will also start to look unruly.


    We require an API test framework where:
    - Test data setup is segregated per test and tests are not reliant on one another. If one test fails, it should never impact others.
    - Only the test data which is relevant to that test case is readable in the test case (default values are implemented)
    - Same for the reponse assertions.
    - As the API develops and changes, large changes are not required to the tests (abstraction and reusable code)
    - Tests are simple to read, write and understand even as they become complex.
    - No fixtures, no verbose POJOs.

To achieve the above, we will implement builder patterns. Director patterns will be used to kick off construction of complex requests with minimal client-side input. Libraries will be implemented to reduce POJO boilerplate (Lombok) and to serialise/de-serialise JSON objects.


## Layers

| Layer          | Description                                                                                                                                                                                           |
|----------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Test Runner    | Runner layer which will kick off tests across different files. In this case, the test running is handled by TestNG.                                                                                   |
| Test Layer     | Class objects per function. Tests are defined and calls to directors are made here. Requests and response calls are made here. Overwritten data needed for each test is also defined here.            |
| Director Layer | Will initialise the builder objects to put together the request using a "recipe" and then pass it back up to the test layer.                                                                          |
| Builder Layer  | These objects create instances of the POJOs to build up the request and then serialise it. In this example, the builders are generated automatically by Lombok and are a subclass of the model layer. |
| Model Layer    | These can have default data, but I chose to define it in the Director Layer. The objects map to the structure of request and response bodies.                                                         |

## API under Test

The API used for these examples is [reqres.in](https://reqres.in/), a publicly-available demo API.
The following endpoints were chosen to create a test suite:

GET`/users` - This endpoint returns a list of all defined users.

POST`/register` - This endpoint is used to set up "new" users. However, in order to get a 200 status, a user which already exists has to be used.

Full swagger documentation is available [here](https://reqres.in/api-docs/).