package com.kaushiksitaraman.StockTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


class ConnTickerBSE {
	
	 private ArrayList<String> price = new ArrayList<String>();
	 private ArrayList<String> price_change = new ArrayList<String>();
	 private ArrayList<String> data = new ArrayList<String>();
	FileData dataconn = new FileData();
	Response response;
	String line;
	
    String responsetext;
    BufferedReader reader;
	
    String temp;
    
	public ArrayList<String> comp_name = new ArrayList<String>();
	public ArrayList<Double> value = new ArrayList<Double>();
	public ArrayList<Double> change_value = new ArrayList<Double>();
	
	String getprice(int index)
	{
		return  price.get(index);
	}
	
	String getprice_change(int index)
	{
		return price_change.get(index);
	}
	
	public String start_connection(String code, int i)
	{
		
		OkHttpClient client = new OkHttpClient();
		System.out.println(dataconn.getKey());
    	Request request = new Request.Builder()
    		.url("https://yahoo-finance-low-latency.p.rapidapi.com/v6/finance/quote?symbols="+code.trim())
    		.get()
    		.addHeader("x-rapidapi-key", dataconn.getKey().trim())
    		.addHeader("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
    		.build();
    		
    	try {
	    		response = client.newCall(request).execute();
	    		temp = response.body().string();
	    		System.out.println(temp);
			} catch (IOException e) {
			
    		return("IOException");
			
		}
    	return temp;
	}
	
    void getdata(String response , int i)
    {	
    	data.add("regularMarketChange");
    	data.add("regularMarketPrice");
    	//-------Setting the beginning values of the for j loop--------//
    for (int k=0 ; k < data.size() ; k++)
    {	
    	String temp2 = data.get(k);
    	int s1 = 0;
    	temp = "";
    	if (response.contains(temp2));
    	{
    		
    		s1 = response.indexOf(temp2);
    		s1 = s1+ data.get(k).length()+2;
    	}
    	//------------ Checking in the response(result)---------//
    	for(int j = s1 ; j<=response.length() ; j++)
    	{
    		
    		if(response.charAt(j) == ',')
    		{	
    			if(temp2.equals("regularMarketChange"))
    			{
    				price_change.add(temp);
    			}
    			else
    			{
    				price.add(temp);
    			}
    			
    			break;
    		}
    		else
    		{	temp = temp+response.charAt(j);
    			
    		}
    	}
    }
    	    	

    }
    
    
}
