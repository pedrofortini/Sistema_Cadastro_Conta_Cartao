# Authorizer

Simple application that authorizes transactions for a specific account following a set of predefined rules.
It was implemented as a standalone Java 8 application.

## Dependencies

- [Java 8](https://www.java.com/pt_BR/download/)
- [Docker](https://docs.docker.com/install/)

## Building

Docker is used for building the application. An image with maven pre-installed is used to build, test and package the 
application. Use the command:

`docker build -t authorizer .`

## Running

To run the application we use the image created on the build process:

`docker run -i authorizer`

OR

`docker run -i authorizer < testInput`

## Context

The application processes stream data coming from `stdin` representing operations on an Account.
The program handles two kinds of operations, deciding on which one according to the line that is being processed:

1.  Account creation
2.  Transaction authorization

Operation data is provided as JSON strings as follows:

1. Account creation:

{"account": {"active-card": true, "available-limit": 100}}

2. Transaction authorization:

{"transaction": {"merchant": "Burger King", "amount": 20, "time": "2019-02-13T10:00:00.000Z"}}

For each operation provided, the application verifies if it breaks any of the following bussines rules:

* There's already an account registered on the system. (Account creation)
* There is no account registered on the system. (Transaction authorization)
* The card on the account is not active.
* The transaction amount does not exceed account's available limit.
* There isn't more than 3 transactions on a 2 minute interval.
* There isn't more than 1 similar transactions (same amount and merchant) in a 2 minutes interval.

If an operation breaks any of the rules, it's not applied to the account. Otherwise, the state of the account 
is changed, reducing it's available limit. The output of the application is a JSON string of the format:

{"account": {"active-card": true, "available-limit": 100}, "violations": \["account-already-initialized"\]}

## Architecture

The application uses the concepts of Event-Driven Architecture and Event-Sourcing. It **reacts** to events coming from 
the input stream, enqueing those events before performing changes on the state of the Account. This way, it's possible 
to **return the Account to it's current state** in case of failures, just by reprocessing the events on the queue.

Following figure shows the interactions between each component of the system.

![alt text](./src/main/resources/Authorizer.png "Authorizer")

Stream data from `stdin` is readed in the main AuthorizerApplication, which instantiates an EventProcessor. The EventProcessor
parses the inputString, identifies the type of event (either an AccountCreationEvent or TransactionAuthorizationEvent),
and calls the event process method. Each event verifies if there is an Account registered on the system, and check for
violations. If there is no violation of Account initiation rules, the correct event handler method on the Account class
is called, based on event type. **Account and Transaction** entities were implemented as **Domain objects**, which means that
they know how to process events and change their state based on that event, so business logic methods are implemented on them.
If an Account find any violation to the business rules, they're returned to the events, which creates the correct 
OutputModel object, printed on the `stdout` by the EventProcessor.

### Important Notes

* Each time the current state of the account is queried, a copy of the account is returned instead of the account itself.
I did it that way to make the **Account immutable**.

* The Account stores all the transactions on a **TreeSet Data Structure**. The TreeSet class allows **sorted insertion** of data.
This is useful to keep account transactions ordered by time.

## Tests

* Unit Tests were implemented using JUnit.
* Integration testing was done by populating a InputStream with data, executing the main method of the AuthorizerApplication
class, and checking data being printed on `stdout`.
* Event-Sourcing recover was tested on EventProcessorTest class, method testingEventSourcingReprocessingMechanism.

### Running

Tests are runned on application build, but they can be executed using `mvn test` command.