package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadProcessJson {

	private static final Logger LOG = Logger.getLogger(ReadProcessJson.class.getName());
	private static List<CurrencyPojo> pojoList = new ArrayList<CurrencyPojo>();
	
	/**Method to render the json data stored in location
	 * @param jsonLoaction
	 */
	public static List<CurrencyPojo> readJson(String jsonLoaction) {

		try {
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(jsonLoaction));

			for (Object obj : jsonArray) {
				JSONObject jsonObj = (JSONObject) obj;

				if (null != jsonObj) {

					String currencyName = (String) jsonObj.get(ProgramConstants.CURRENCY);
					if (null != currencyName && currencyName.equalsIgnoreCase(ProgramConstants.CURRENCY_TYPE_BTC)) {

						getJsonValues(jsonObj, currencyName);

					} else if (currencyName.equalsIgnoreCase(ProgramConstants.CURRENCY_TYPE_ETH)) {

						getJsonValues(jsonObj, currencyName);

					} else if (currencyName.equalsIgnoreCase(ProgramConstants.CURRENCY_TYPE_LTC)) {

						getJsonValues(jsonObj, currencyName);
					}

				}

			}
		} catch (Exception e) {
			LOG.info("Exception in method readJson: "+e);
		}

		return pojoList;
	}

	/**Method to get JsonValues as String from the JsonObject
	 * @param jsonObj
	 * @param currencyName
	 */
	private static void getJsonValues(JSONObject jsonObj, String currencyName) {
		try {
			CurrencyPojo currencyPojo = null;
			QuotePojo quotePojo = null;
			currencyPojo = new CurrencyPojo();
			currencyPojo.setCurrencyName(currencyName);

			if (null != jsonObj.get(ProgramConstants.DATE)) {
				String date = (String) jsonObj.get(ProgramConstants.DATE);
				currencyPojo.setDate(date);
			}
			JSONArray quotes = (JSONArray) jsonObj.get(ProgramConstants.QUOTES);
			if (null != quotes) {
				List<QuotePojo> list = new ArrayList<QuotePojo>();
				for (Object quote : quotes) {
					quotePojo = new QuotePojo();
					JSONObject quoteJson = (JSONObject) quote;
					String time = (String) quoteJson.get(ProgramConstants.TIME);
					quotePojo.setQuotetime(time);

					double price = Double.parseDouble((String) quoteJson.get(ProgramConstants.PRICE));
					quotePojo.setQuoteprice(price);
					list.add(quotePojo);

				}
				currencyPojo.setListuotepojos(list);
			}
			pojoList.add(currencyPojo);
		} catch (Exception e) {
			LOG.info("Exception in getJsonValues "+e);
		}
	}

}
