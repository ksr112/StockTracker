package com.kaushiksitaraman.StockTracker;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


class ConnStockQuote {
	
	private String temp="";
    Response response;
    private ArrayList<String> data = new ArrayList<String>();
    ArrayList<String> values = new ArrayList<String>();
	private String stock = "";
	FileData dataconn = new FileData();
	
    void setstock(String x)
    {
    	stock = x;
    }
    
    void defstock()
    {
    	stock = "";
    }
	
	 //----------finding each entries value in the response to fill the labels------------//  
    void getdata(String x)
    {	

    	//------- Checking the rest of the needed values by adding to arraylist and having 2 for-loops to minimalize code----------//
    	data.add("regularMarketChange");
    	data.add("regularMarketChangePercent");
    	data.add("regularMarketPrice");
    	data.add("regularMarketDayHigh");
    	data.add("regularMarketDayLow");
    	data.add("regularMarketOpen");
    	data.add("regularMarketPreviousClose");
    	data.add("marketCap");
    	//-------Setting the beginning values of the for j loop--------//
    for (int i=0 ; i < data.size() ; i++)
    {	int s1 = 0;
    	temp = "";
    	if (x.contains(data.get(i)));
    	{
    		
    		s1 = x.indexOf(data.get(i));
    		s1 = s1+ data.get(i).length()+2;
    	}
    	//------------ Checking in the response(result)---------//
    	for(int j = s1 ; j<=x.length() ; j++)
    	{
    		
    		if(x.charAt(j) == ',')
    		{
    			values.add(i, temp);;
    			break;
    		}
    		else
    		{	temp = temp+x.charAt(j);
    			
    		}
    	}
    }

    	
    
    }

	public String get_longname(String x)
	{
	
		String find = "longName";
		String text = null;
		boolean exit = false;
		if(x.contains(find))
		{
			int index = x.indexOf(find);
			for( int i = index ; i < x.length() ; i++ )	
			{	
				
				if(x.charAt(i) == ':')
				{
					for( int j = i+2 ; j < x.length() ; j++)
					{
						if(x.charAt(j) == '"')
						{
							text = text.substring(4 , text.length());
							exit = true;
							return text;
							
						}
						else
						{	
							
							text = text + x.charAt(j);
							continue;
						}
					}
					
				}
				
				else if (exit)
				{
					break;
				}
				else
				{
					continue;
				}
				
			}
		}
		else
		{
			
		}
		
		return null;
	}
	
	
	String get_connection() throws IOException
	{	OkHttpClient client = new OkHttpClient();

    	Request request = new Request.Builder()
    		.url("https://yahoo-finance-low-latency.p.rapidapi.com/v6/finance/quote?symbols="+stock.trim())
    		.get()
    		.addHeader("x-rapidapi-key", dataconn.getKey().trim())
    		.addHeader("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
    		.build();
    		
    	try {
    		response = client.newCall(request).execute();
    		temp = response.body().string();
			
    	} catch (IOException e) {
			
    		return("IOException");
			
		}
    	return temp;
	}
	
	String get_connection(String key, String stock) throws IOException
	{	OkHttpClient client = new OkHttpClient();

    	Request request = new Request.Builder()
    		.url("https://yahoo-finance-low-latency.p.rapidapi.com/v6/finance/quote?symbols="+stock.trim())
    		.get()
    		.addHeader("x-rapidapi-key", key)
    		.addHeader("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
    		.build();
    		
    	try {
    		response = client.newCall(request).execute();
    		temp = response.body().string();
			if(temp.contains("{\"result\":[],\"error\":null}"))
			{
				return("IOException");
			}
    		
    	} catch (IOException e) {
			
    		return("IOException");
			
		}
    	return temp;
	}

}
