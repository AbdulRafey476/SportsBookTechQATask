The following is a real-life case that allows you to demonstrate your ability to use SoapUI.
You need to demonstrate the ability to handle a data driven test, process different processing paths, and accurately log results.

Case 1 – Automation
Retrieve and persist a list of random cities distributed worldwide (we expect this number to consist of several thousand cities). Furthermore, create a framework capable of requesting weather, and currency/forex data using but not limited to open source API’s like:
http://openweathermap.org/api; http://www.xignite.com/forex;
Create a data generator which builds on the city data previously retrieved. This tool should generate a dataset of randomised people (this set is expected to be in thousands), and populate the following fields:
“Name, Surname, Address, City, Telephone, Email, Date of birth, Sex, Marital Status, Nationality, Country of birth, Gross annual income, and Currency”. This data must be built against standard regex patterns.
Create a script in SoapUI (ideally using groovy) which can execute the following steps for each person in the list:
1. Retrieve current weather depending on the combination of city and country.
2. Retrieve gross annual income, convert that in euros, and divide this income by a weekly basis.
3. If the person is in a rainy city, and this has been so for a foreseeable 3 day range (-1, current, +1):
• Generate a list of recommended cities to visit which have better weather conditions. A city has favourable weather conditions if it is sunny for at least the current day.
• If the person is in a city with good weather conditions then proceed with the next person.

```Solution:
To be honest I don't give a clue how via SoapUI I would need do this :)
As I was told by Jan that in this task I can even use any other tool, so I used Java.
```
You may find list of recommended cities in [src/main/resources/recommendedSunnyCitiesList.txt](src/main/resources/recommendedSunnyCitiesList.txt)



Case 2 – Performance
Without any implementation needed, how would you execute the following different types of performance tests on any endpoint of your choosing?
• Load testing,
• Stress testing,
• Endurance testing,
• Spike testing,
• Volume testing, and
• Scalability testing.
How would you store your result set, what KPI’s would you be after, and how could you make the tests cons