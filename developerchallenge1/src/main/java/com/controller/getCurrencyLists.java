package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.model.CurrencyPojo;
import com.model.ReadProcessJson;
import com.view.DisplayCurrencyProfits;

public class getCurrencyLists {

	private static final Logger LOG = Logger.getLogger(getCurrencyLists.class.getName());
	public static void getCurrencyDetails() {
		
		List<CurrencyPojo> list = new ArrayList<CurrencyPojo>();
		try {

			list = ReadProcessJson.readJson("C:/Amith/NABChallenge/challenge1/data/20180507.json");
			DisplayCurrencyProfits.displayCurrencies(list);
		} catch (Exception e) {
			LOG.info("Exception in class: getCurrencyDetails.java "+e);
		}
	}

}
