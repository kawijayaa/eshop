# Eshop (Tutorial)

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