# Bike rental application

This project is a program to manage bicycle rentals. It enables the creation of road bikes and electric bikes, adding new bikes to the company's collection, renting out bikes, and terminating rental contracts. The program also keeps track of customer records and which customer has rented which bike with a maximum of 1 per customer [^1]. All customers can rent road bikes, but only 'Gold Class' customers that are at least 21 years old can rent electric bikes.

The program was created as part of the *Software Development Advanced Techniques* module of the MSc Computer Science course at Newcastle University in February 2021 but has been revised and updated in October 2022.

## UML class diagram

<img src="https://github.com/kidijkmans/bike-rental-application/blob/main/UML-class-diagram.jpg" width="500">

## Getting started

1. Install [Java](https://www.oracle.com/java/technologies/downloads/#java17)

2. Download the code in a ZIP file or clone the repository

``` $ git clone https://github.com/kidijkmans/bike-rental-application.git ```

3. Open `RentalManager.java` (located in *src/main/java/kd*) in an IDE

4. Run the `main` method to start the program

5. Modify the `debug` method to test out different scenarios

[^1]: Disclaimer: All customer data is fictional, no personal information is used in the program.