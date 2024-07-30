This project to test  Amazon Website

List of main tools and libraries used:

- Maven
- Junit5
- Selenium-Java
- Allure-Junit5
- AssertJ
--------------------------------------------
Tests covered:
- Search by Keyword (Brand name) and check all results have the brand name in their description
- Search by Category and check all resukts have the category name in their description
- Sorting (by price from low to high) and check sort is done correctly
- Filter results (by Fulfilled by amazon) and check that all items are filtered correctly
- Add items to the cart and check the number of added items same as the actual added items
- Calculate price in the Shopping cart and Verify the calculation of total prices
- Checkout
---------------------------------------------
Generating the report
You may generate a report using: allure serve target/allure-results 
