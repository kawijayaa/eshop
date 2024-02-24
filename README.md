# Eshop (Tutorial)

[Deployed App](https://eshop-muhammadoka.koyeb.app/)

## Reflection 1 - Module 1
I think my code is sufficiently clean and secure. For clean code practices, I used descriptive names for variables and method/functions. And also I structure my code so that it is self-documenting.
For secure code practices, I implemented the product ID using UUID so that it cannot be enumerated.
Also, when editing a product I forced the product given to the method to use the old UUID to prevent duplicate ID's.
I need to improve my commit messages, since some of them are not very descriptive or informative.

## Reflection 2 - Module 1
1. I feel like I am still very bad at writing tests.
   How many unit tests in a class depends on how many features it has.
   100% coverage doesn't guarantee bug-free code, since the tests can also be buggy.

2. The new test could be repetitive since the new tests is doing similar things. We can refactor the new functional suites to include the prior setups and instance variables.

## Reflection - Module 2
1. One code quality issue I got is wrong argument order on assertEquals(). The intended use is assertEquals(expected, actual) but instead I used assertEquals(actual, expected).
   So I needed to swap the arguments to the intended argument order.

2. Yes, I do think my implementation has met the definition of CI/CD. 
   For CI, I have implemented testing tools such as JUnit, OSSF Scorecard and Sonarcloud to make sure that the newly updated codebase is nicely integrated and verified. 
   For CD, I have implemented automatic deployment to Koyeb using a Dockerfile.

## Reflection - Module 3
1. I applied the Liskov Substitution Principle, Open-Closed Principle and Dependency Inversion Principle. The Liskov Substitution Principle
said that a superclass should be able to substitute for a subclass without breaking functionality. The Open-Closed Principle said that a software
entity should be open to development/extension by other developers, but not open to modification of the actual source code. The Dependency Inversion
Principle said that high-level modules should depend on abstractions of the low-level modules, not the concrete implementation of the low-level modules.
The rest of the SOLID principles (SRP, ISP) in my opinion are already implemented. An example of the SRP implementation is separating Car and Product 
modules (repositories, services, controllers). An example of the ISP implementation is limiting interfaces to their basic functionality (CRUD).

2. One of the advantages of SOLID principles is they make sure the code that we write can be developed further by other developers. Since
I refactored the repository to use interfaces, other developers can add new repository implementations such as using a database for the
repository

3. When not applying SOLID principles, it can be hard to use and develop other developers' code. For example if I always force set a UUID to every product,
which violates the Open-Closed Principle then it can only support UUID and not other ID types such as sequential integer ID.