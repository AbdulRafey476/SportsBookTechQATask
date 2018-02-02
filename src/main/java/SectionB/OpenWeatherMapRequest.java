package SectionB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OpenWeatherMapRequest {
static String urlWeatherForecast = "http://api.openweathermap.org/data/2.5/forecast?q=";
static String urlCurrentWeather = "http://api.openweathermap.org/data/2.5/weather?q=";
static String token = "&appid=62f337782478a6160a252c0fe01dd56b";

	static Logger logger = Logger.getLogger(OpenWeatherMapRequest.class.getName());

	public static List<String> cityWeatherForecastList(String city, String country) {

		try {

			URL url = new URL(urlWeatherForecast + city + ","
					+ country + token);

			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String jsonLine = reader.readLine();

			List<String> weatherDesc = new ArrayList<String>();

			if (jsonLine.length() > 0) {

				JSONObject jsonObjectList = (JSONObject) JSONValue.parse(jsonLine);
				JSONArray jsonArrayList = (JSONArray) jsonObjectList.get("list");

				for (int i = 0; i < jsonArrayList.size(); i++) {

					String jsonStringFromjsonArrayList = jsonArrayList.get(i).toString();
					JSONObject jsonObjectWeather = (JSONObject) JSONValue.parse(jsonStringFromjsonArrayList);
					JSONArray jsonArrayWeather = (JSONArray) jsonObjectWeather.get("weather");

					for (int j = 0; j < jsonArrayWeather.size(); j++) {

						String jsonStringWeatherDesc = jsonArrayWeather.get(j).toString();
						JSONObject jsonObjectWeatherDesc = (JSONObject) JSONValue.parse(jsonStringWeatherDesc);
						weatherDesc.add((String) jsonObjectWeatherDesc.get("main"));

					}

					if (i == 7) {
						logger.info("Weather forecast for " + city);
						break;

					}

				}
				return weatherDesc;

			} else {
				logger.severe("Get request error");
			}
			reader.close();
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		return null;

	}

	public static String cityCurrentWeather(String city, String country) {

		try {

			URL url = new URL(urlCurrentWeather + city + ","
					+ country + token);

			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String jsonLine = reader.readLine();

			String currentWeatherDesc = null;

			if (jsonLine.length() > 0) {

				JSONObject jsonObjectList = (JSONObject) JSONValue.parse(jsonLine);
				JSONArray jsonArrayListWeather = (JSONArray) jsonObjectList.get("weather");

				for (int i = 0; i < jsonArrayListWeather.size(); i++) {

					String jsonStringFromjsonArrayList = jsonArrayListWeather.get(i).toString();
					JSONObject jsonObjectWeather = (JSONObject) JSONValue.parse(jsonStringFromjsonArrayList);
					currentWeatherDesc = (String) jsonObjectWeather.get("main");
				}
				logger.info("Current weather for " + city);
				return currentWeatherDesc.toLowerCase();

			} else {
				logger.severe("Get request error");
			}
			reader.close();
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		return null;

	}
}
