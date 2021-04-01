package com.kaushiksitaraman.StockTracker;

public class Stock {
	
	private String symbol;
	
	private String name;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stock(String symbol, String name) {
		super();
		this.symbol = symbol;
		this.name = name;
	}
	
	public Stock()
	{
		super();
	}
	
	
}
