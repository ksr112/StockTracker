package com.kaushiksitaraman.StockTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class ConnTickerBSE {
  private static HttpURLConnection connection;
  
  private ArrayList<String> price = new ArrayList<>();
  
  private ArrayList<String> price_change = new ArrayList<>();
  
  private StringBuffer responsecontent = new StringBuffer();
  
  String line;
  
  String responsetext;
  
  BufferedReader reader;
  
  public ArrayList<String> comp_name = new ArrayList<>();
  
  public ArrayList<Double> value = new ArrayList<>();
  
  public ArrayList<Double> change_value = new ArrayList<>();
  
  String getprice(int index) {
    return this.price.get(index);
  }
  
  String getprice_change(int index) {
    return this.price_change.get(index);
  }
  
  public String start_connection(String code, int i) {
    try {
      this.responsetext = "";
      URL url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/" + code.trim() + "?region=IN&lang=en-IN&corsDomain=in.finance.yahoo.com&.tsrc=finance");
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(5000);
      int status = connection.getResponseCode();
      if (status > 299) {
        this.reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        while ((this.line = this.reader.readLine()) != null)
          this.responsecontent.append(this.line); 
        this.reader.close();
      } else {
        this.reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((this.line = this.reader.readLine()) != null)
          this.responsetext = this.line; 
        this.reader.close();
        return this.responsetext;
      } 
    } catch (MalformedURLException malformedURLException) {
    
    } catch (IOException e) {
      return "IOException";
    } finally {
      connection.disconnect();
    } 
    return null;
  }
  
  void getdata(String response, int i) {
    String first = "regularMarketPrice";
    String second = "chartPreviousClose";
    String temp = "";
    int position = 0;
    if (response.contains(first)) {
      position = response.indexOf(first);
      position = position + first.length() + 2;
      for (int j = position; j < response.length(); j++) {
        if (response.charAt(j) == ',') {
          this.price.add(i, temp);
          temp = "";
          position = 0;
          break;
        } 
        temp = temp + response.charAt(j);
      } 
    } 
    if (response.contains(second)) {
      position = response.indexOf(second);
      position = position + second.length() + 2;
      for (int j = position; j < response.length(); j++) {
        if (response.charAt(j) == ',') {
          Double transfer = Double.valueOf(Double.valueOf(this.price.get(i)).doubleValue() - Double.valueOf(temp).doubleValue());
          this.price_change.add(transfer.toString());
          temp = "";
          transfer = Double.valueOf(0.0D);
          position = 0;
          break;
        } 
        temp = temp + response.charAt(j);
      } 
    } 
  }
}