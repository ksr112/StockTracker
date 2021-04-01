package com.kaushiksitaraman.StockTracker;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class ConnStockGraph {

	
	private String stock_code;
	private String range;
	private String interval;
	private String resp_result="";
	ArrayList<String> date_timedt = new ArrayList<String>();
	ArrayList<String> date_dtmnth = new ArrayList<String>();
	ArrayList<String> date_mnthyr = new ArrayList<String>();
	ArrayList<String> date_dtmnthyr = new ArrayList<String>();
	ArrayList<Double> values = new ArrayList<Double>();
	protected Object s;
	private FileData dataconn = new FileData();
	
    private static final Logger log = LoggerFactory.getLogger(FirstController.class);	
    
	ConnStockGraph()
	{
		//--------Default Constructor to avoid compilation failure
	}
	
	ConnStockGraph( String stock_code , String range , String interval )
	{
		//--- Constructor to initialize the values for the request before making the request
		
		this.stock_code = stock_code;
		this.range = range;
		this.interval = interval;
		
	}
	
	
	void converthex(String temp)
	{
		
		Date time=new Date((long)Long.valueOf(temp)*1000);
		LocalDateTime localDate = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		date_mnthyr.add(String.valueOf(localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US))+","+String.valueOf(localDate.getYear()));
		date_timedt.add(String.valueOf(localDate.getHour())+":"+String.valueOf(localDate.getMinute())+" "+ String.valueOf(localDate.getDayOfMonth())+","+String.valueOf(localDate.getMonth().getDisplayName(TextStyle.SHORT,  Locale.US)));
		date_dtmnth.add( String.valueOf(localDate.getDayOfMonth())+" "+String.valueOf(localDate.getMonth().getDisplayName(TextStyle.SHORT,  Locale.US)));
		date_dtmnthyr.add(  String.valueOf(localDate.getDayOfMonth())+" "+String.valueOf(localDate.getMonth().getDisplayName(TextStyle.SHORT,  Locale.US))+" "+String.valueOf(localDate.getYear()));
	}
	void gettime(String stockresponse)
    {
    	    		
    		String s = "timestamp";
        	int s1 = 0;
        	if (stockresponse.contains(s))
        	{
        		
        		s1 = stockresponse.indexOf(s);
        		s1 = s1+ s.length()+3;
        	}
        	String temp ="";
        	for(int i = s1 ; i < stockresponse.length() ; i++)
        	{   
        		
        		if(stockresponse.charAt(i) == ',')
        		{
        			
        			
        			converthex(temp);
        			temp = "";
        			continue;
        			
        		}
        		else if(stockresponse.charAt(i) == ']')
        		{
        			if(temp != null)
        			{
        				converthex(temp);
        				temp = "";
            			break;
        			}
        			else
        			{
        				break;
        			}
        			
        		}
        		else if((stockresponse.charAt(i) == 'n') && (stockresponse.charAt(i+1) == 'u') && (stockresponse.charAt(i+2) == 'l') && (stockresponse.charAt(i+3) == 'l') )
        		{
        			break;
        		}
        		else
        		{	temp = temp+stockresponse.charAt(i);
        			
        		}
        	}
    	}
	
	 void getdata(String stockresponse)
	    {
	    	    		
	    		String s = "close";
	        	int s1 = 0;
	        	if (stockresponse.contains(s))
	        	{
	        		
	        		s1 = stockresponse.indexOf(s);
	        		s1 = s1+ s.length()+3;
	        	}
	        	String temp ="";
	        	for(int i = s1 ; i < stockresponse.length() ; i++)
	        	{   
	        		
	        		if(stockresponse.charAt(i) == ',')
	        		{
	        			values.add(Double.valueOf(temp));
	        			temp = "";
	        			continue;
	        			
	        		}
	        		else if((stockresponse.charAt(i) == 'n') && (stockresponse.charAt(i+1) == 'u') && (stockresponse.charAt(i+2) == 'l') && (stockresponse.charAt(i+3) == 'l') )
	        		{
	        			break;
	        		}
	        		else if(stockresponse.charAt(i) == ']')
	        		{
	        			values.add(Double.valueOf(temp));
	        			temp = "";
	        			break;
	        		}
	        		else
	        		{	temp = temp+stockresponse.charAt(i);
	        			
	        		}
	        	}
	    	}
	
	String start_connection()
	{
		OkHttpClient client = new OkHttpClient();
		log.debug("Sending request");
    	Request request = new Request.Builder()
    		.url("https://yahoo-finance-low-latency.p.rapidapi.com/v8/finance/spark?symbols="+ stock_code.trim() +"&range="+ range.trim() +"&interval="+ interval.trim())
    		.get()
    		.addHeader("x-rapidapi-key", dataconn.getKey().trim())
    		.addHeader("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
    		.build();
		try {
			Response response = client.newCall(request).execute();
			resp_result = response.body().string();
			return resp_result;
			//debug
			
			
			
		} catch (IOException e) {
			return("IOException");
		}
	}
	
}
