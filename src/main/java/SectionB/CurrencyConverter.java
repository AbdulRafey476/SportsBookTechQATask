package SectionB;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

public class CurrencyConverter {

	static Logger logger = Logger.getLogger(CurrencyConverter.class.getName());

	public static Double findExchangeRateAndConvert(String fromCurrency, String toCurrency, float amount) {
		try {

			URL url = new URL("http://globalcurrencies.xignite.com/xGlobalCurrencies.json/ConvertRealTimeValue?From="
					+ fromCurrency + "&To=" + toCurrency + "&amount=" + amount + "&_token="
					+ "13688E60836649CD9BC99B47ED836456");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = reader.readLine();

			if (line.length() > 0) {

				JSONObject jsonObject = (JSONObject) JSONValue.parse(line);
				Double currencyDetails = (Double) jsonObject.get("Result");
				return currencyDetails;
			} else {
				logger.severe("Request error");
			}
			reader.close();
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		System.out.println("Currency successfully converted to EUR");
		return null;
	}
}
