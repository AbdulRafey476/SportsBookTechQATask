The following is a real-life case that allows you to demonstrate your ability to use SoapUI.
You need to demonstrate the ability to handle a data driven test, process different processing paths, and accurately log results.

# Case 1 – Automation
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

# Solution:
To be honest I couldn't imagine how via SoapUI I would solve this task.

As I was told by Jan that in this task I can even use any other tool, so I used Java.

SoupUI dependencies and test [src/test/java/SoupUiTest.java](src/test/java/SoupUiTest.java) was added to the project.

To run data generator please run: [src/main/java/SectionB/DataGenerator.java](src/main/java/SectionB/DataGenerator.java)

For data generator was used mockaroo API.

You may find saved dataset of randomised people in [src/main/resources/randomizedPeopleDataset.json](src/main/resources/randomizedPeopleDataset.json)

To retrieve gross annual income, converted in EUR, and divided by a weekly basis please run [src/main/java/SectionB/SunnyCityAdviser.java](src/main/java/SectionB/SunnyCityAdviser.java)

You may find generated list of recommended cities in [src/main/resources/recommendedSunnyCitiesList.txt](src/main/resources/recommendedSunnyCitiesList.txt)
```
WARNING: 
As the free openweathermap API is limited generated list of recommended cities wouldn't contain more then 5 cases. 
Also openweathermap API retrieve weather information about some cities in the list.
```

# Case 2 – Performance
Without any implementation needed, how would you execute the following different types of performance tests on any endpoint of your choosing?
• Load testing,
• Stress testing,
• Endurance testing,
• Spike testing,
• Volume testing, and
• Scalability testing.
How would you store your result set, what KPI’s would you be after, and how could you make the tests cons

# Solution:
• Load Testing
Load testing includes increasing the load and see how the system behaves under higher load. 

We can monitor response times, throughput, server conditions, and more.

The goal is to find metrics for system performance under high load.

In or case:
 * mockaroo API is limited for 1000 rows to download (Free);
 * openweathermap API is limited to 60 calls per minute (Free);
 * xignite API 250 hits for 7 days (Free Trial, valid to )

• Stress Testing
The goal of stress test is exactly that, to find the load volume where the system actually breaks or is close to breaking.

So we need to find the system breaking point.

As you may check openweathermap API breaks after 60 calls because of Free Plan limitation.

And in the and count of advices of SunnyWetherAdvicer get only 5 cases ([src/main/resources/recommendedSunnyCitiesList.txt](src/main/resources/recommendedSunnyCitiesList.txt))

• Scalability Testing
The purpose of scalability testing is to check whether your system scales appropriately to the changing load. 

For example, a larger number of incoming requests should cause proportial increase in the response time. 

In the end we can use metrics and check if the system performance changes appropriately to the load.

Test could be runned from [src/test/java/SoupUiTest.java](src/test/java/SoupUiTest.java)

Test results would be stored to [SportBookTechQATask-soapui-project.xml](SportBookTechQATask-soapui-project.xml)

Question regarding "KPI’s would you be after, and how could you make the tests consistently reproducible on demand" - is really depends of the project needs and purposes.