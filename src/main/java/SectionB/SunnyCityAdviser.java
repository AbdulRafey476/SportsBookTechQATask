package SectionB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SunnyCityAdviser {

	static Logger logger = Logger.getLogger(SunnyCityAdviser.class.getName());

	private static String city;
	private static float grossAnnualIncome;
	private static String currency;
	private static String country;

	static List<String> sunnyCityList = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		StringBuilder sunnyCityAdviser = sunnyCityAdviser();

		String recommendedCitiesListFile = new File(
				"src/main/resources/recommendedSunnyCitiesList.txt").getAbsolutePath();
		saveFile(sunnyCityAdviser, recommendedCitiesListFile);

	}

	public static void saveFile(StringBuilder builder, String filePath) throws IOException {

		try (FileWriter file = new FileWriter(filePath)) {
			file.write(builder.toString());
		}
	}

	private static StringBuilder sunnyCityAdviser() {

		StringBuilder sunnyCityAdviser = new StringBuilder();

		JSONParser parser = new JSONParser();

		try {

			Object object = parser.parse(new FileReader(
					"src/main/resources/randomizedPeopleDataset.json"));

			float weeksCount = 52;

			String newLine = System.getProperty("line.separator");

			JSONArray jasonArray = (JSONArray) object;

			for (int i = 0; i < jasonArray.size(); i++) {

				String jsonString = jasonArray.get(i).toString();
				JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString);

				sunnyCityAdviser.append("Person " + (i + 1) + System.lineSeparator());
				sunnyCityAdviser.append("Name : " + (String) jsonObject.get("Name") + System.lineSeparator());
				city = (String) jsonObject.get("City");
				sunnyCityAdviser.append("City : " + city + System.lineSeparator());
				country = (String) jsonObject.get("CountryOfBirth");
				sunnyCityAdviser.append("Country : " + country + System.lineSeparator());



				grossAnnualIncome = Float.parseFloat(String.valueOf(jsonObject.get("GrossAnnualIncome")));
				currency = (String) jsonObject.get("Currency");
				sunnyCityAdviser.append("Gross Annual Income : " + grossAnnualIncome + " " + currency + "\n");
				Double grossAnnualIncomeInEUR = CurrencyConverter.findExchangeRateAndConvert(currency, "EUR",
						grossAnnualIncome);
				sunnyCityAdviser.append("Gross Annual Income in EUR : " + grossAnnualIncomeInEUR + "EUR" + "\n");
				sunnyCityAdviser.append("Weekly Income in EUR : " + Math.round(grossAnnualIncomeInEUR / weeksCount) + " EUR" + "\n");

				List<String> cityWeatherDescription = new ArrayList<String>();

				cityWeatherDescription = WeatherRequest.cityWeatherForecastList(city, country);

				if (cityWeatherDescription.contains("Rain")) {
					List<String> suggestedCityList = new ArrayList<String>();
					suggestedCityList = recommendedSunnyCityList(jasonArray);
					sunnyCityAdviser.append("Person is in a rainy city" + System.lineSeparator());
					sunnyCityAdviser.append("Recommended sunny city/cities: " + System.lineSeparator());

					String prefix = "";
					if (!suggestedCityList.isEmpty()) {
						for (String city : suggestedCityList) {
							sunnyCityAdviser.append(prefix);
							prefix = ",";
							sunnyCityAdviser.append(city);
						}
						sunnyCityAdviser.append(System.lineSeparator() +
                                "#######################################"
                                + System.lineSeparator());

						logger.info("List of sunny cities suggested");
					} else {
						sunnyCityAdviser.append("Person lives in sunny city, no proposals to visit" + (i + 1) + " then " + city + System.lineSeparator());
						logger.info("Person lives in sunny city, no proposals to visit");
					}

				} else {

					sunnyCityAdviser.append("Person not in a rainy city" + System.lineSeparator());
					sunnyCityAdviser.append(newLine + System.lineSeparator());
				}

			}

		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			logger.severe("Randomized people dataset file not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sunnyCityAdviser;
	}

	private static List<String> recommendedSunnyCityList(JSONArray jasonArray) {

		if (sunnyCityList.isEmpty()) {

			for (int j = 0; j < jasonArray.size(); j++) {

				String jsonStringForCurrentWeather = jasonArray.get(j).toString();
				JSONObject jsonObjectForCurrentWeather = (JSONObject) JSONValue.parse(jsonStringForCurrentWeather);

				city = (String) jsonObjectForCurrentWeather.get("City");
				country= (String) jsonObjectForCurrentWeather.get("Country");
				String currentWeather = WeatherRequest.cityCurrentWeather(city, country);
				if (currentWeather != null)
					if (currentWeather.contains("sunny") || currentWeather.contains("clear")) {
						sunnyCityList.add(city);
					}
			}
		}

		return sunnyCityList;
	}

}
