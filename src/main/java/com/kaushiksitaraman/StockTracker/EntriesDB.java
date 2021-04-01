package com.kaushiksitaraman.StockTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntriesDB {
    	
	 	Statement st;
	 	
		private static final String[] bse_Names = {"S&P BSE SENSEX","ACC Ltd(L)",
	    		"Adani Ports and Special Economic Zone Ltd(L)",
	    		"Ambuja Cements Ltd(L)",
	    		"Apollo Hospitals Enterprise Ltd(L)",
	    		"Ashok Leyland Ltd(L)",
	    		"Asian Paints Ltd(L)",
	    		"Aurobindo Pharma Ltd(L)",
	    		"Avenue Supermarts Ltd(L)",
	    		"Axis Bank Ltd(L)",
	    		"Bajaj Auto Ltd(L)",
	    		"Bajaj Finance Ltd(L)",
	    		"Bajaj Finserv Ltd(L)",
	    		"Bajaj Holdings & Investment Ltd(L)",
	    		"Bank Of Baroda(L)",
	    		"Berger Paints India Ltd(L)",
	    		"Bharat Forge Ltd(L)",
	    		"Bharat Petroleum Corporation Ltd(L)",
	    		"Bharti Airtel Ltd(L)",
	    		"Biocon Ltd(L)",
	    		"Bosch Ltd(L)",
	    		"Britannia Industries Ltd(L)",
	    		"Cipla Ltd(L)",
	    		"Coal India Ltd(L)",
	    		"Colgate-Palmolive (India) Ltd(L)",
	    		"Container Corporation Of India Ltd(L)",
	    		"Crompton Greaves Consumer Electricals Ltd(L)",
	    		"Dabur India Ltd(L)",
	    		"Divis Laboratories Ltd(L)",
	    		"DLF Ltd(L)",
	    		"Dr. Reddys Laboratories Ltd(L)",
	    		"Eicher Motors Ltd(L)",
	    		"Exide Industries Ltd(M)",
	    		"GAIL (India) Ltd(L)",
	    		"Godrej Consumer Products Ltd(L)",
	    		"Grasim Industries Ltd(L)",
	    		"Havells India Ltd(L)",
	    		"HCL Technologies Ltd(L)",
	    		"HDFC Bank Ltd(L)",
	    		"HDFC Life Insurance Co Ltd(L)",
	    		"Hero MotoCorp Ltd(L)",
	    		"Hindalco Industries Ltd(L)",
	    		"Hindustan Petroleum Corporation Ltd(L)",
	    		"Hindustan Unilever Ltd(L)",
	    		"ICICI Bank Ltd(L)",
	    		"ICICI Prudential Life Insurance Company Ltd(L)",
	    		"Indian Oil Corporation Ltd(L)",
	    		"Indraprastha Gas Ltd(L)",
	    		"IndusInd Bank Ltd(L)",
	    		"Info Edge (India) Ltd(L)",
	    		"Infosys Ltd(L)",
	    		"Interglobe Aviation Ltd(L)",
	    		"ITC Ltd(L)",
	    		"JSW Steel Ltd(L)",
	    		"Jubilant FoodWorks Ltd(L)",
	    		"Kotak Mahindra Bank Ltd(L)",
	    		"Larsen & Toubro Ltd(L)",
	    		"LIC Housing Finance Ltd(M)",
	    		"Lupin Ltd(L)",
	    		"Mahindra & Mahindra Financial Services Ltd(L)",
	    		"Mahindra & Mahindra Ltd(L)",
	    		"Marico Ltd(L)",
	    		"Maruti Suzuki India Ltd(L)",
	    		"Motherson Sumi Systems Ltd(L)",
	    		"MRF Ltd(L)",
	    		"Nestle India Ltd(L)",
	    		"NMDC Ltd(L)",
	    		"NTPC Ltd(L)",
	    		"Oil & Natural Gas Corporation Ltd(L)",
	    		"Page Industries Ltd(L)",
	    		"Petronet LNG Ltd(L)",
	    		"Pidilite Industries Ltd(L)",
	    		"Piramal Enterprises Ltd(L)",
	    		"Power Finance Corporation Ltd(L)",
	    		"Power Grid Corporation Of India Ltd(L)",
	    		"RBL Bank Ltd(M)",
	    		"REC Ltd(L)",
	    		"Reliance Industries Ltd(L)",
	    		"SBI Life Insurance Company Ltd(L)",
	    		"Shree Cement Ltd(L)",
	    		"Shriram Transport Finance Company Ltd(L)",
	    		"Siemens Ltd(L)",
	    		"State Bank Of India(L)",
	    		"Sun Pharmaceutical Industries Ltd(L)",
	    		"Tata Consultancy Services Ltd(L)",
	    		"Tata Consumer Products Ltd(L)",
	    		"Tata Motors - DVR Ordinary-",
	    		"Tata Motors Ltd(L)",
	    		"Tata Power Company Ltd(L)",
	    		"Tata Steel Ltd(L)",
	    		"Tech Mahindra Ltd(L)",
	    		"The Federal Bank Ltd(M)",
	    		"Titan Company Ltd(L)",
	    		"TVS Motor Company Ltd(L)",
	    		"Ultratech Cement Ltd(L)",
	    		"UPL Ltd(L)",
	    		"Vedanta Ltd(L)",
	    		"Voltas Ltd(L)",
	    		"Wipro Ltd(L)",
	    		"Zee Entertainment Enterprises Ltd(L)"};
		
		private static final String[] bse_Codes = {"^BSESN","ACC.BO",
	    		"ADANIPORTS.BO",
	    		"AMBUJACEM.BO",
	    		"APOLLOHOSP.BO",
	    		"ASHOKLEY.BO",
	    		"ASIANPAINT.BO",
	    		"AUROPHARMA.BO",
	    		"DMART.BO",
	    		"AXISBANK.BO",
	    		"BAJAJ-AUTO.BO",
	    		"BAJFINANCE.BO",
	    		"BAJAJFINSV.BO",
	    		"BAJAJHLDNG.BO",
	    		"BANKBARODA.BO",
	    		"BERGEPAINT.BO",
	    		"BHARATFORG.BO",
	    		"BPCL.BO",
	    		"BHARTIARTL.BO",
	    		"BIOCON.BO",
	    		"BOSCHLTD.BO",
	    		"BRITANNIA.BO",
	    		"CIPLA.BO",
	    		"COALINDIA.BO",
	    		"COLPAL.BO",
	    		"CONCOR.BO",
	    		"CROMPTON.BO",
	    		"DABUR.BO",
	    		"DIVISLAB.BO",
	    		"DLF.BO",
	    		"DRREDDY.BO",
	    		"EICHERMOT.BO",
	    		"EXIDEIND.BO",
	    		"GAIL.BO",
	    		"GODREJCP.BO",
	    		"GRASIM.BO",
	    		"HAVELLS.BO",
	    		"HCLTECH.BO",
	    		"HDFC.BO",
	    		"HDFCLIFE.BO",
	    		"HEROMOTOCO.BO",
	    		"HINDALCO.BO",
	    		"HINDPETRO.BO",
	    		"HINDUNILVR.BO",
	    		"ICICIBANK.BO",
	    		"ICICIPRULI.BO",
	    		"IOC.BO",
	    		"IGL.BO",
	    		"INDUSINDBK.BO",
	    		"NAUKRI.BO",
	    		"INFY.BO",
	    		"INDIGO.BO",
	    		"ITC.BO",
	    		"JSWSTEEL.BO",
	    		"JUBLFOOD.BO",
	    		"KOTAKBANK.BO",
	    		"LT.BO",
	    		"LICHSGFIN.BO",
	    		"LUPIN.BO",
	    		"M%26MFIN.BO",
	    		"M%26M.BO",
	    		"MARICO.BO",
	    		"MARUTI.BO",
	    		"MOTHERSUMI.BO",
	    		"MRF.BO",
	    		"NESTLEIND.BO",
	    		"NMDC.BO",
	    		"NTPC.BO",
	    		"ONGC.BO",
	    		"PAGEIND.BO",
	    		"PETRONET.BO",
	    		"PIDILITIND.BO",
	    		"PEL.BO",
	    		"PFC.BO",
	    		"POWERGRID.BO",
	    		"RBLBANK.BO",
	    		"RECLTD.BO",
	    		"RELIANCE.BO",
	    		"SBILIFE.BO",
	    		"SHREECEM.BO",
	    		"SRTRANSFIN.BO",
	    		"SIEMENS.BO",
	    		"SBIN.BO",
	    		"SUNPHARMA.BO",
	    		"TCS.BO",
	    		"TATACONSUM.BO",
	    		"TATAMTRDVR.BO",
	    		"TATAMOTORS.BO",
	    		"TATAPOWER.BO",
	    		"TATASTEEL.BO",
	    		"TECHM.BO",
	    		"FEDERALBNK.BO",
	    		"TITAN.BO",
	    		"TVSMOTOR.BO",
	    		"ULTRACEMCO.BO",
	    		"UPL.BO",
	    		"VEDL.BO",
	    		"VOLTAS.BO",
	    		"WIPRO.BO",
	    		"ZEEL.BO" };
		
		private static final String[] nse_Names = {"NIFTY 50","Abbott India Ltd(L)",
	    		"ACC Ltd(L)",
	    		"Adani Green Energy Ltd(L)",
	    		"Adani Ports and Special Economic Zone Ltd(L)",
	    		"Adani Transmission Ltd(L)",
	    		"Alkem Laboratories Ltd(L)",
	    		"Ambuja Cements Ltd(L)",
	    		"Asian Paints Ltd(L)",
	    		"Aurobindo Pharma Ltd(L)",
	    		"Avenue Supermarts Ltd(L)",
	    		"Axis Bank Ltd(L)",
	    		"Bajaj Auto Ltd(L)",
	    		"Bajaj Finance Ltd(L)",
	    		"Bajaj Finserv Ltd(L)",
	    		"Bajaj Holdings & Investment Ltd(L)",
	    		"Bandhan Bank Ltd(L)",
	    		"Bank Of Baroda(L)",
	    		"Berger Paints India Ltd(L)",
	    		"Bharat Petroleum Corporation Ltd(L)",
	    		"Bharti Airtel Ltd(L)",
	    		"Biocon Ltd(L)",
	    		"Bosch Ltd(L)",
	    		"Britannia Industries Ltd(L)",
	    		"Cadila Healthcare Ltd(L)",
	    		"Cipla Ltd(L)",
	    		"Coal India Ltd(L)",
	    		"Colgate-Palmolive (India) Ltd(L)",
	    		"Container Corporation Of India Ltd(L)",
	    		"Dabur India Ltd(L)",
	    		"Divis Laboratories Ltd(L)",
	    		"DLF Ltd(L)",
	    		"Dr. Reddys Laboratories Ltd(L)",
	    		"Eicher Motors Ltd(L)",
	    		"GAIL (India) Ltd(L)",
	    		"General Insurance Corporation of India Ltd(L)",
	    		"Godrej Consumer Products Ltd(L)",
	    		"Grasim Industries Ltd(L)",
	    		"Havells India Ltd(L)",
	    		"HCL Technologies Ltd(L)",
	    		"HDFC Asset Management Company Ltd(L)",
	    		"HDFC Bank Ltd(L)",
	    		"HDFC Life Insurance Co Ltd(L)",
	    		"Hero MotoCorp Ltd(L)",
	    		"Hindalco Industries Ltd(L)",
	    		"Hindustan Petroleum Corporation Ltd(L)",
	    		"Hindustan Unilever Ltd(L)",
	    		"Hindustan Zinc Ltd(L)",
	    		"Housing Development Finance Corporation Ltd(L)",
	    		"ICICI Bank Ltd(L)",
	    		"ICICI Lombard General Insurance Co Ltd(L)",
	    		"ICICI Prudential Life Insurance Company Ltd(L)",
	    		"Indian Oil Corporation Ltd(L)",
	    		"Indraprastha Gas Ltd(L)",
	    		"IndusInd Bank Ltd(L)",
	    		"Info Edge (India) Ltd(L)",
	    		"Infosys Ltd(L)",
	    		"Interglobe Aviation Ltd(L)",
	    		"ITC Ltd(L)",
	    		"JSW Steel Ltd(L)",
	    		"Kotak Mahindra Bank Ltd(L)",
	    		"Larsen & Toubro Infotech Ltd(L)",
	    		"Larsen & Toubro Ltd(L)",
	    		"Lupin Ltd(L)",
	    		"Mahindra & Mahindra Ltd(L)",
	    		"Marico Ltd(L)",
	    		"Maruti Suzuki India Ltd(L)",
	    		"Motherson Sumi Systems Ltd(L)",
	    		"Muthoot Finance Ltd(L)",
	    		"Nestle India Ltd(L)",
	    		"NMDC Ltd(L)",
	    		"NTPC Ltd(L)",
	    		"Oil & Natural Gas Corporation Ltd(L)",
	    		"Oracle Financial Services Software Ltd(L)",
	    		"Petronet LNG Ltd(L)",
	    		"Pidilite Industries Ltd(L)",
	    		"Piramal Enterprises Ltd(L)",
	    		"Power Finance Corporation Ltd(L)",
	    		"Power Grid Corporation Of India Ltd(L)",
	    		"Procter & Gamble Hygiene & Health Care Ltd(L)",
	    		"Punjab National Bank(L)",
	    		"Reliance Industries Ltd(L)",
	    		"SBI Cards And Payment Services Ltd(L)",
	    		"SBI Life Insurance Company Ltd(L)",
	    		"Shree Cement Ltd(L)",
	    		"Siemens Ltd(L)",
	    		"State Bank Of India(L)",
	    		"Sun Pharmaceutical Industries Ltd(L)",
	    		"Tata Consultancy Services Ltd(L)",
	    		"Tata Consumer Products Ltd(L)",
	    		"Tata Motors Ltd(L)",
	    		"Tata Steel Ltd(L)",
	    		"Tech Mahindra Ltd(L)",
	    		"Titan Company Ltd(L)",
	    		"Torrent Pharmaceuticals Ltd(L)",
	    		"Ultratech Cement Ltd(L)",
	    		"United Breweries Ltd(L)",
	    		"United Spirits Ltd(L)",
	    		"UPL Ltd(L)",
	    		"Wipro Ltd(L)"};
			
		private static final String[] nse_Codes = {"^NSEI","ABBOTINDIA.NS",
	    		"ACC.NS",
	    		"ADANIGREEN.NS",
	    		"ADANIPORTS.NS",
	    		"ADANITRANS.NS",
	    		"ALKEM.NS",
	    		"AMBUJACEM.NS",
	    		"ASIANPAINT.NS",
	    		"AUROPHARMA.NS",
	    		"DMART.NS",
	    		"AXISBANK.NS",
	    		"BAJAJ-AUTO.NS",
	    		"BAJFINANCE.NS",
	    		"BAJAJFINSV.NS",
	    		"BAJAJHLDNG.NS",
	    		"BANDHANBNK.NS",
	    		"BANKBARODA.NS",
	    		"BERGEPAINT.NS",
	    		"BPCL.NS",
	    		"BHARTIARTL.NS",
	    		"BIOCON.NS",
	    		"BOSCHLTD.NS",
	    		"BRITANNIA.NS",
	    		"CADILAHC.NS",
	    		"CIPLA.NS",
	    		"COALINDIA.NS",
	    		"COLPAL.NS",
	    		"CONCOR.NS",
	    		"DABUR.NS",
	    		"DIVISLAB.NS",
	    		"DLF.NS",
	    		"DRREDDY.NS",
	    		"EICHERMOT.NS",
	    		"GAIL.NS",
	    		"GICRE.NS",
	    		"GODREJCP.NS",
	    		"GRASIM.NS",
	    		"HAVELLS.NS",
	    		"HCLTECH.NS",
	    		"HDFCAMC.NS",
	    		"HDFCBANK.NS",
	    		"HDFCLIFE.NS",
	    		"HEROMOTOCO.NS",
	    		"HINDALCO.NS",
	    		"HINDPETRO.NS",
	    		"HINDUNILVR.NS",
	    		"HINDZINC.NS",
	    		"HDFC.NS",
	    		"ICICIBANK.NS",
	    		"ICICIGI.NS",
	    		"ICICIPRULI.NS",
	    		"IOC.NS",
	    		"IGL.NS",
	    		"INDUSINDBK.NS",
	    		"NAUKRI.NS",
	    		"INFY.NS",
	    		"INDIGO.NS",
	    		"ITC.NS",
	    		"JSWSTEEL.NS",
	    		"KOTAKBANK.NS",
	    		"LTI.NS",
	    		"LT.NS",
	    		"LUPIN.NS",
	    		"M%26M.NS",
	    		"MARICO.NS",
	    		"MARUTI.NS",
	    		"MOTHERSUMI.NS",
	    		"MUTHOOTFIN.NS",
	    		"NESTLEIND.NS",
	    		"NMDC.NS",
	    		"NTPC.NS",
	    		"ONGC.NS",
	    		"OFSS.NS",
	    		"PETRONET.NS",
	    		"PIDILITIND.NS",
	    		"PEL.NS",
	    		"PFC.NS",
	    		"POWERGRID.NS",
	    		"PGHH.NS",
	    		"PNB.NS",
	    		"RELIANCE.NS",
	    		"SBICARD.NS",
	    		"SBILIFE.NS",
	    		"SHREECEM.NS",
	    		"SIEMENS.NS",
	    		"SBIN.NS",
	    		"SUNPHARMA.NS",
	    		"TCS.NS",
	    		"TATACONSUM.NS",
	    		"TATAMOTORS.NS",
	    		"TATASTEEL.NS",
	    		"TECHM.NS",
	    		"TITAN.NS",
	    		"TORNTPHARM.NS",
	    		"ULTRACEMCO.NS",
	    		"UBL.NS",
	    		"MCDOWELL-N.NS",
	    		"UPL.NS",
	    		"WIPRO.NS"};;
		
	    private static final Logger log = LoggerFactory.getLogger(FirstController.class);
	    		
	    public Connection getBSEConnection() throws ClassNotFoundException, SQLException {
	        log.debug("Getting a database connection");
	        Class.forName("org.h2.Driver");
	        return DriverManager.getConnection("jdbc:h2:~/bsedb;mode=PostgreSQL;AUTO_SERVER=TRUE");
	      }
	    
	    public Connection getNSEConnection() throws ClassNotFoundException, SQLException {
	        log.debug("Getting a database connection");
	        Class.forName("org.h2.Driver");
	        return DriverManager.getConnection("jdbc:h2:~/nsedb;mode=PostgreSQL;AUTO_SERVER=TRUE");
	      }
	    
	    public void create_bse_db(Connection conn) throws SQLException
	    {
	    	
	    	log.debug("Creating databse for BSE data");
	    	
			try {
				 st = conn.createStatement();
				 String table = "create table BSE(symbol varchar(20) NOT NULL, name varchar(80) NOT NULL , constraint PK PRIMARY KEY (symbol))";
		    	 st.executeUpdate(table);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            if(st != null){
	                st.close();
	            }
	    	}
	    	   
	    	    	    	
	    }
	    public void fill_bse_db(Connection conn) throws SQLException
	    {
	    	log.debug("Filling BSE database");      
	        Statement st = null;
			try {
				st = conn.createStatement();
				for (int i = 0 ; i < bse_Codes.length ; i++) {
		        	
					st.executeUpdate("insert into BSE values(" + "'" + bse_Codes[i] + "'" + "," + "'" + bse_Names[i] + "'" + ")" );
		        	
		        }
		        log.debug("Done.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            if(st != null){
	                st.close();
	            }
	    	}
	       
	    }
	    
	    public void create_nse_db(Connection conn) throws SQLException
	    {
	    	
	    	log.debug("Creating databse for NSE data");
	    	try {
				 st = conn.createStatement();
				 String table = "create table NSE(symbol varchar(20) NOT NULL , name varchar(80) NOT NULL , constraint PK PRIMARY KEY (symbol))";
		    	 st.executeUpdate(table);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            if(st != null){
	                st.close();
	            }
	    	}
	    	
	    }
	   
	    public void add_db_row(Connection conn , String table , String Symbol , String Name ) throws SQLException
	    {
	    	log.debug("Adding row to the database");
		    	try {
		    		Statement st = conn.createStatement();
		    		st.executeUpdate("Insert into "+table+" values ( '"+Symbol+"','"+Name+"');");
		    	}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		     	} finally {
	            if(st != null){
	                st.close();
	            }
		     }
	    	
	    }
	    
	    public void delete_db_entry(Connection conn , String table , String Symbol) throws SQLException
	    {
	    	log.debug("Removing a row from the database");
	    	try {
		    	Statement st = conn.createStatement();      
			    st.executeUpdate("DELETE FROM "+ table +" WHERE symbol = "+"'"+Symbol +"'"+";" );
	        		
		     	} catch (SQLException e) {
		     		e.printStackTrace();
		     	} finally {
		     		if(st != null){
		     			st.close();
	            }
	    	}
	    }
	    
	    public void fill_nse_db(Connection conn) throws SQLException
	    {
	    	log.debug("Filling NSE database");      
	       try {
	    	Statement st = conn.createStatement();      
		    for (int i = 0 ; i < nse_Codes.length ; i++) {
	       	
	        		st.executeUpdate("insert into NSE values(" + "'" + nse_Codes[i] + "'" + "," + "'" + nse_Names[i] + "'" + ")" );
	        		
		        }
		     } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            if(st != null){
	                st.close();
	            }
	    	}
	    }
	    
	    public Boolean dbExists(Connection conn , String table) throws SQLException
	    {
	    	log.debug("Checking whether db exists or not");
	    	 try {
	    	      Statement st = conn.createStatement();     
	    	      st.executeQuery("select count(*) from " + table);
	    	      log.debug("Db exists.");      
	    	     
	    	    } catch (SQLException ex) {
	    	      log.debug("Db not found.");
	    	      return false;
	    	    } finally {
		            if(st != null){
		                st.close();
		            }
		    	}
	    	return true;	
	    }
	    
	   public ObservableList<String> getSymbol( Connection conn , String table ) throws SQLException
	    {
	    	log.debug("Fetching the symbol list from "+table+" table");
	    	ObservableList<String> symbols =  FXCollections.observableArrayList();
	    	
	    
	    	     try {
	    	    		Statement st = conn.createStatement();
	    		    	ResultSet result =  st.executeQuery("select symbol from "+table+" order by symbol");
	    		    	  while (result.next()) {
					symbols.add(result.getString("symbol"));
	    		    	  }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
	    	    } finally {
		            if(st != null){
		                st.close();
		            }
				}
			return symbols;

	    }
	    
	    public ObservableList<String> getName( Connection conn , String table ) throws SQLException
	    {
	    	log.debug("Fetching the symbol list from "+table+" table");
	    	ObservableList<String> names =  FXCollections.observableArrayList();
	    	
	    
	    	     try {
	    	    		Statement st = conn.createStatement();
	    		    	ResultSet result =  st.executeQuery("select name from "+table+" order by symbol");
	    		    	  while (result.next()) {
					names.add(result.getString("name"));
	    		    	  }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
	    	    } finally {
		            if(st != null){
		                st.close();
		            }
		    	}
			return names;

	    }
	    
	    public static ObservableList<Stock> bsedata_top(ObservableList<Stock> bseData)
	    {
	    	String symbol = bseData.get(bseData.size()-1).getSymbol() ;
	    	String name = bseData.get(bseData.size()-1).getName();
	    	if(symbol.equals("^BSESN"))
	    	{
		    	bseData.remove(bseData.size()-1);
		    	bseData.add(0 , new Stock(symbol , name));
		    }
			return bseData;
	    	
	    }
	    
	    public static ObservableList<Stock> getBSEdata(Connection conn) throws SQLException {
	        
	            Statement st = null;
	            ObservableList<Stock> bseData = null;
	            try {
	            	st = conn.createStatement();
	 	            ResultSet rs = st.executeQuery("select * from BSE Order by symbol");
	 	        
	 	            bseData = FXCollections.observableArrayList();
	 	        
	            	while (rs.next()) {
					    bseData.add(new Stock(rs.getString("Symbol") , rs.getString("Name")));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
		            if(st != null){
		                st.close();
		            }
		    	}
	            bseData = bsedata_top(bseData);
	            return bseData ;
	        } 
	    
	    public static ObservableList<Stock> nsedata_top(ObservableList<Stock> nseData)
	    {
	    	String symbol = nseData.get(nseData.size()-1).getSymbol() ;
	    	String name = nseData.get(nseData.size()-1).getName();
	    	if(symbol.equals("^NSEI"))
	    	{
		    	nseData.remove(nseData.size()-1);
		    	nseData.add(0 , new Stock(symbol , name));
		    }
			return nseData;
	    	
	    }
	    
	    public static ObservableList<Stock> getNSEdata(Connection conn) throws SQLException {
	        
            Statement st =  null;
            ObservableList<Stock> nseData = null;
            try {
	            st = conn.createStatement();
	            ResultSet rs = st.executeQuery("select * from NSE Order by symbol");
	            nseData = FXCollections.observableArrayList();
	            while (rs.next()) {
	                nseData.add(new Stock(rs.getString("Symbol") , rs.getString("Name")));
	            }
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            if(st != null){
	                st.close();
	            }
			}
            nseData = nsedata_top(nseData);
            return  nseData;
        }
	  }

	    

