package com.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.model.CurrencyPojo;

public class DisplayCurrencyProfits {
	
	private static final Logger LOG = Logger.getLogger(DisplayCurrencyProfits.class.getName());

	/**
	 * Method to display values
	 * @param list
	 */
	public static void displayCurrencies(List<CurrencyPojo> list) {

		List<Double> sellingPricesList = null;
		List<Double> profitList = null;
		List<String> quoteTimeList = null;

		try {
			if (null != list && list.size() != 0) {

				for (int index = 0; index < list.size(); index++) {
					
					double buyingPrice = 0.0;
					String buyingTime = null;
					double sellingPrice = 0.0;
					double profit = 0.0;
					sellingPricesList = new ArrayList<Double>();
					profitList = new ArrayList<Double>();
					quoteTimeList = new ArrayList<String>();

					System.out.println(list.get(index).getCurrencyName());
					System.out.println("------------------------------------------------------------------------\n");
					System.out.println("BUY"+ "\t" + "BUY-TIME" + "\t" + "SELL" + "\t" + "SELL-TIME" + "\t" + "PROFIT \n");
					
					for (int index1 = 0; index1 < list.get(index).getListuotepojos().size(); index1++) {
						if (index1 == 0) {
							buyingPrice = list.get(index).getListuotepojos().get(index1).getQuoteprice();
							buyingTime = convertTime(list.get(index).getListuotepojos().get(index1).getQuotetime());
						}

						if (index1 > 0) {
							sellingPrice = list.get(index).getListuotepojos().get(index1).getQuoteprice();
							profit = sellingPrice - buyingPrice;
							sellingPricesList.add(sellingPrice);
							Math.round(profit);
							profitList.add((double) Math.round(profit));

							String time = convertTime(list.get(index).getListuotepojos().get(index1).getQuotetime());
							quoteTimeList.add(time);

						}
					}

					for (int j = 0; j < sellingPricesList.size(); j++) {

						System.out.printf("$" +buyingPrice + "\t"+ buyingTime+ "\t\t" + "$" + sellingPricesList.get(j) + "\t" + quoteTimeList.get(j) + "\t\t" + "$" +profitList.get(j)+"\t"+ "\n");
					}
					
					System.out.println("\n");
				}

			}
		} catch (Exception e) {
			LOG.info("Exception in displayCurrencies method: "+e);
		}
	}
	
	/**
	 * Method to convert 24hour time to 12 hour time
	 * @param str
	 * @return
	 */

	public static String convertTime(String str) {

		String time = null;
		try {
			
			int hour = Integer.parseInt(str.substring(0, 2));
			if (hour < 12) {
				time = str.substring(0, 2) + ":" + str.substring(2, 4) + "AM";
			} else if (hour >= 12) {
				if (hour > 12) {
					hour = hour - 12;
				}
				time = String.valueOf(hour) + ":" + str.substring(2, 4) + "PM";
			}
		} catch (Exception e) {
			LOG.info("Exception in convertTime method: "+e);
		}
		return time;
	}
}
