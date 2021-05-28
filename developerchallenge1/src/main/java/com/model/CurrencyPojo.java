package com.model;

import java.util.List;

public class CurrencyPojo {

	private String currencyName;
	private String date;
	private List<QuotePojo> quotePojo;
	
	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<QuotePojo> getListuotepojos() {
		return quotePojo;
	}

	public void setListuotepojos(List<QuotePojo> listuotepojos) {
		this.quotePojo = listuotepojos;
	}
}
