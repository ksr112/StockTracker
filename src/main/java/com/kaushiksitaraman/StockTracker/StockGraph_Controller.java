package com.kaushiksitaraman.StockTracker;

import java.util.ArrayList;
import org.controlsfx.control.textfield.TextFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

public class StockGraph_Controller {

    @FXML
    private ComboBox<String> cmbbox_list;

    @FXML
    private RadioButton rdbtn_BSE;

    @FXML
    private RadioButton rdbtn_NSE;

    @FXML
    private ComboBox<String> cmbbox_range;

    @FXML
    private ComboBox<String> cmbbox_interval;
    
    @FXML
    private ScrollPane Scroll;

    @FXML
    private AreaChart<String, Double> StockChart;
    
    @FXML
    private CategoryAxis chartdate;
    
    @FXML
    private Label lbl_range;

    @FXML
    private Label lbl_interval;

    @FXML
    private Button btn_ok;

    @FXML
    private Button btn_reset;
    
    @FXML
    private Pane pane1;
    
    private String[] arrBSE = {"S&P BSE SENSEX","ACC Ltd(L)",
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
    		"Bharti Infratel Ltd(L)",
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
    		"Housing Development Finance Corporation Ltd(L)",
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
    
    private String[] arrBSEcodes = {"^BSESN","ACC.BO",
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
    		"INFRATEL.BO",
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
    		"HDFC.BO",
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
      
    private String[] arrNSE = {"NIFTY 50","Abbott India Ltd(L)",
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
    		"Bharti Infratel Ltd(L)",
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
    
    private String[] arrNSEcodes = {"^NSEI","ABBOTINDIA.NS",
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
    		"INFRATEL.NS",
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
    		"WIPRO.NS"};
    
    private String[] arrRange = { "1d","5d","3mo","6mo","1y","5y","max"};
    
    private String[] arrInterval0 = { "1m","5m","15m" };
    
    private String[] arrInterval1 = { "1m","5m","15m","1d" };
    
    private String[] arrIntervalmax = { "1mo" };
    
    private String[] arrIntervalrest = { "1d","1wk","1mo" };
    
    private static final Logger log = LoggerFactory.getLogger(FirstController.class);
    
    private String key_present ;
    
    private FileData dataconn = new FileData();
    
    Thread taskThread;
    
    String stockresponse;
    
    public void initialize()
    {	
    	log.debug("Disabling the range combobox, enabled when combobox list is not null");
    	lbl_range.disableProperty().bind(cmbbox_list.valueProperty().isNull());
		cmbbox_range.disableProperty().bind(cmbbox_list.valueProperty().isNull());
    	
    	log.debug("Filling the range combobox with values");
    	ObservableList<String> range = FXCollections.observableArrayList(arrRange);
		cmbbox_range.setItems(range);
    	
		log.debug("Disabling the interval combobox, enabled when combobox list is not null");
		lbl_interval.disableProperty().bind(cmbbox_list.valueProperty().isNull().or(cmbbox_list.editorProperty().isNull()));
		cmbbox_interval.disableProperty().bind(cmbbox_list.valueProperty().isNull());
		
		log.debug("Disabling the ok button, enabled when main combobox is filled");
		btn_ok.disableProperty().bind(
    			cmbbox_interval.valueProperty().isNull()
    	);
		log.debug("Disabling the reset button, enabled when main combobox is filled");
    	btn_reset.disableProperty().bind(
    			cmbbox_interval.valueProperty().isNull()
    	);
    	
    	log.debug("Now disabling interval label and combobox till range is not fed in ");
    	cmbbox_interval.disableProperty().bind(cmbbox_range.valueProperty().isNull());
    	lbl_interval.disableProperty().bind(cmbbox_range.valueProperty().isNull());
    	
    	cmbbox_list.getSelectionModel().selectedIndexProperty().addListener(getListener());
    	
    	cmbbox_range.getSelectionModel().selectedIndexProperty().addListener(getListener_range());
    	
    	log.debug("Checking the theme in place");
    	if(dataconn.getTheme().equals("dark"))
    	{
    		pane1.setStyle("-fx-background-color:#232323;");
    		
    		lbl_range.setStyle("-fx-text-fill:white;");
    		lbl_interval.setStyle("-fx-text-fill:white;");
    		Scroll.setStyle("-fx-border-color:white");
    	}
    	else
    	{
    		pane1.setStyle("-fx-background-color:white;");
    		lbl_range.setStyle("-fx-text-fill:black;");
    		lbl_interval.setStyle("-fx-text-fill:black;");
    		Scroll.setStyle("-fx-border-color:#232323");
    		
    	}
    	switch (dataconn.getSecTheme())
    	{
    		case "Lime": Theme_set("Lime"); 
    					 break;
    		case "Purple": Theme_set("Purple");
    					   break;
    		case "Turquoise": Theme_set("Turquoise");
    						  break;
    		case "Blue": Theme_set("Blue");
    					 break;
    		case "Orange":Theme_set("Orange");
    					   break;
    	}
    	
    	log.debug("Checking whether key has been implemented and saved");
    	
    	if(!dataconn.getKey().equals("none"))
    	{	
    		
    		key_present = "yes";
    		
    	}
    	else
    	{	


	        	 log.debug("Provide notification to state to feed in the API key by visiting settings before being able to use this service");
	        	 key_present = "no";
	        	 
    		
    	}
    	
    }
    
    void Theme_set(String colour)
    {
    	if(dataconn.getTheme().equals("white"))
    		{
    			cmbbox_range.getStyleClass().add("cmbbox-white-"+colour);
    			cmbbox_interval.getStyleClass().add("cmbbox-white-"+colour);
    			cmbbox_list.getStyleClass().add("cmbbox-white-"+colour);
    			rdbtn_NSE.getStyleClass().add("rdbtn-white-"+colour);
    			rdbtn_BSE.getStyleClass().add("rdbtn-white-"+colour);
    			btn_ok.getStyleClass().add("btn-white-"+colour);
    			btn_reset.getStyleClass().add("btn-white-"+colour);
    			StockChart.getStyleClass().add("area-chart-white-"+colour);
    		}
    		else
    		{
    			
    			cmbbox_range.getStyleClass().add("cmbbox-dark-"+colour);
    			cmbbox_interval.getStyleClass().add("cmbbox-dark-"+colour);
    			cmbbox_list.getStyleClass().add("cmbbox-dark-"+colour);
    			rdbtn_NSE.getStyleClass().add("rdbtn-dark-"+colour);
    			rdbtn_BSE.getStyleClass().add("rdbtn-dark-"+colour);
    			btn_ok.getStyleClass().add("btn-dark-"+colour);
    			btn_reset.getStyleClass().add("btn-dark-"+colour);
    			StockChart.getStyleClass().add("area-chart-dark-"+colour);
    		}
    	}
    
    void makeStockChart(ArrayList<String> date,ArrayList<Double> value,XYChart.Series<String,Double> Stockseries,String legend )
    {
    	String date_temp = "";Double values_temp = 0.0;
    	for( int i=0 ; i < value.size()-1;i++)
		{	
			date_temp = "";values_temp = 0.0;
			values_temp = value.get(i);
			date_temp = date.get(i);
			XYChart.Data<String, Double > Stockdata = new XYChart.Data<String, Double>(date_temp, values_temp);
			Stockseries.getData().add(Stockdata);
			
		}
		Stockseries.setName(legend);
    	
    }
    
    
    @FXML
    void Select_Bse(ActionEvent event) {
    	
    	if(rdbtn_BSE.isSelected()== true)
    	{
    		log.debug("Deselecting NSE");
    		rdbtn_NSE.setSelected(false);
    		log.debug("Hiding NSE radiobutton");
    		rdbtn_NSE.setVisible(false);
    		log.debug("relocating bse radiobutton so as to get it in center");
    		rdbtn_BSE.relocate(295, 33);
    		log.debug("relocating NSE radiobutton to its usual position as a failsafe");
    		rdbtn_NSE.relocate(376, 33);
    		log.debug("Putting BSE values in combobox and enabling it");
    		ObservableList<String> bsedata = FXCollections.observableArrayList(arrBSE);
			cmbbox_list.setItems(bsedata);
			cmbbox_list.setDisable(false);
			cmbbox_list.setPromptText("Select the BSE Stock");
			TextFields.bindAutoCompletion(cmbbox_list.getEditor(), cmbbox_list.getItems());
			
    	}
    	else
    	{
    		log.debug("Deselecting NSE");
    		rdbtn_NSE.setSelected(false);
    		log.debug("expose NSE radiobutton");
    		rdbtn_NSE.setVisible(true);
    		log.debug("relocating BSE radiobutton to its usual position");
    		rdbtn_BSE.relocate(222, 33);
    		log.debug("Disabling and removing the combox entries");
    		cmbbox_list.getSelectionModel().clearSelection();
    		cmbbox_list.getItems().clear();
			cmbbox_list.setDisable(true);
			cmbbox_list.setPromptText("Select BSE or NSE");
			log.debug("Bringing the range combobox value back to null");
			cmbbox_range.getSelectionModel().clearSelection();
			log.debug("Bringing the interval combobox values back to null");
			cmbbox_interval.getSelectionModel().clearSelection();
			
    	}
    	
    }

    @FXML
    void Select_Nse(ActionEvent event) {

    	if(rdbtn_NSE.isSelected()== true)
		{
			log.debug("Deselecting BSE");
			rdbtn_BSE.setSelected(false);
			log.debug("Hiding BSE radiobutton");
			rdbtn_BSE.setVisible(false);
			log.debug("relocating NSE radiobutton so as to get it in center");
			rdbtn_NSE.relocate(295 , 33);
			log.debug("relocating BSE radiobutton to its usual position as a failsafe");
			rdbtn_BSE.relocate(222, 33);
			log.debug("Putting NSE values in combobox and enabling it");
			ObservableList<String> nsedata = FXCollections.observableArrayList(arrNSE);
			cmbbox_list.setItems(nsedata);
			cmbbox_list.setDisable(false);
			cmbbox_list.setPromptText("Select the NSE Stock");
			TextFields.bindAutoCompletion(cmbbox_list.getEditor(), cmbbox_list.getItems());
			
    	}
		else
		{
			log.debug("Deselecting BSE");
			rdbtn_BSE.setSelected(false);
			log.debug("expose BSE radiobutton");
			rdbtn_BSE.setVisible(true);
			log.debug("relocating NSE radiobutton to its usual position");
			rdbtn_NSE.relocate(376, 33);
			log.debug("Disabling and removing the combox entries");
			cmbbox_list.getSelectionModel().clearSelection();
    		cmbbox_list.getItems().clear();
			cmbbox_list.setDisable(true);
			cmbbox_list.setPromptText("Select BSE or NSE");
			log.debug("Disabling all the labels as a fail safe");
			log.debug("Bringing the range combobox value back to null");
			cmbbox_range.getSelectionModel().clearSelection();
			log.debug("Bringing the interval combobox values back to null");
			cmbbox_interval.getSelectionModel().clearSelection();
		}
    	
    }
    
    //----------Action Listener for the combobox range so whenever it's value is changed it changes the value of interval 
    //----------and loads it for validation and to avoid errors during response.
   
    
    private ChangeListener<? super Number> listener_range = (observable, oldValue, newValue) -> {
        if( cmbbox_range.getSelectionModel().getSelectedIndex() == 0 )
        {
            Platform.runLater(() -> {
            	cmbbox_interval.getSelectionModel().clearSelection();
            	ObservableList<String> interval = FXCollections.observableArrayList(arrInterval0);
        		cmbbox_interval.setItems(interval);
        		
            });
        }
        else if( cmbbox_range.getSelectionModel().getSelectedIndex() == 1 )
        {
            Platform.runLater(() -> {
            	cmbbox_interval.getSelectionModel().clearSelection();
            	ObservableList<String> interval = FXCollections.observableArrayList(arrInterval1);
        		cmbbox_interval.setItems(interval);
            });
        }
        else if( cmbbox_range.getSelectionModel().getSelectedIndex() == 6 )
        {
            Platform.runLater(() -> {
            	cmbbox_interval.getSelectionModel().clearSelection();
            	ObservableList<String> interval = FXCollections.observableArrayList(arrIntervalmax);
        		cmbbox_interval.setItems(interval);
        		cmbbox_interval.getSelectionModel().select(0);
            });
        }
        else
        {
        	Platform.runLater(() -> {
        		cmbbox_interval.getSelectionModel().clearSelection();
        		ObservableList<String> interval = FXCollections.observableArrayList(arrIntervalrest);
        		cmbbox_interval.setItems(interval);
        	});
        }
        
    };
    
    private ChangeListener<? super Number> getListener_range() {
        return listener_range;
    }
    //----------Action Listener for the combobox so whenever it's value is changed, it checks if the line chart is visible,
    //----------if yes then it hides the line chart and the scroll pane and gets the (range & interval) comboboxes and label 
    //----------back into focus
   
    private ChangeListener<? super Number> listener = (observable, oldValue, newValue) -> {
        if(Scroll.isVisible() == true && StockChart.isVisible() == true )
        {
            Platform.runLater(() -> {
            	log.debug("Hiding the stockchart and the Scroll pane");
        		Scroll.setVisible(false);
        		StockChart.setVisible(false);
        		log.debug("Removing the chart already present on it");
        		StockChart.getData().clear();
        		log.debug("Exposing the labels and comboboxes");
    			log.debug("1.//.......range label");
    			lbl_range.setVisible(true);
    			log.debug("2.//.......range combobox");
    			cmbbox_range.setVisible(true);
    			log.debug("2.5.//......getting it to it's default form");
    			cmbbox_range.getSelectionModel().clearSelection();
    			log.debug("3.//.......interval label");
    			lbl_interval.setVisible(true);
    			log.debug("3.5.//........getting it to it's default form");
    			cmbbox_interval.getSelectionModel().clearSelection();
    			log.debug("4.//.......interval combobox");
    			cmbbox_interval.setVisible(true);
                cmbbox_list.getSelectionModel().selectedIndexProperty().addListener(getListener());
            });
        }
    };
    
    private ChangeListener<? super Number> getListener() {
        return listener;
    }
    
   
    @FXML
    void reset(ActionEvent event) {
    	
    	log.debug("resetting the rdbtn bse value to false");
    	rdbtn_BSE.setVisible(true);
    	log.debug("resetting the rdbtn bse selected value to false");
    	rdbtn_BSE.setSelected(false);
    	log.debug("relocating rdbtn bse");
    	rdbtn_BSE.relocate(222, 33);
    	log.debug("resetting the rdbtn nse value to false");
    	rdbtn_NSE.setVisible(true);
    	log.debug("resetting the rdbtn nse selected value to false");
    	rdbtn_NSE.setSelected(false);
    	log.debug("relocating rdbtn nse");
    	rdbtn_NSE.relocate(376, 33);
    	log.debug("Disabling and removing the combox entries");
    	cmbbox_list.getSelectionModel().clearSelection();
		cmbbox_list.getItems().clear();
		cmbbox_list.setDisable(true);
		cmbbox_list.setPromptText("Select BSE or NSE");
		log.debug("Getting range combobox value to null");
		cmbbox_range.getSelectionModel().clearSelection();
		log.debug("Getting interval combobox value back to null");
		cmbbox_interval.getSelectionModel().clearSelection();
		log.debug("Exposing range label and combobox");
		log.debug("1.//.......range label");
		lbl_range.setVisible(true);
		log.debug("2.//.......range combobox");
		cmbbox_range.setVisible(true);
		log.debug("3.//.......interval label");
		lbl_interval.setVisible(true);
		log.debug("4.//.......interval combobox");
		cmbbox_interval.setVisible(true);
		log.debug("Hiding Scrollpane and line chart");
		Scroll.setVisible(false);
		StockChart.setVisible(false);
		log.debug("Removing the previous entry in line chart");
		StockChart.getData().clear();
    }
    
    @FXML
    void ok(ActionEvent event) {
    	//----------Checking whether both range and interval comboboxes are not null.
    	if(key_present.equals("yes"))
    	{
	    	//----------Validation to see whether the interval surpasses the range.
	    		if((cmbbox_range.getSelectionModel().getSelectedIndex() == 0 && cmbbox_interval.getSelectionModel().getSelectedIndex() == 4) || ( cmbbox_range.getSelectionModel().getSelectedIndex() == 0 && cmbbox_interval.getSelectionModel().getSelectedIndex() == 5 ) || (cmbbox_range.getSelectionModel().getSelectedIndex() == 1 && cmbbox_interval.getSelectionModel().getSelectedIndex() == 4) || ( cmbbox_range.getSelectionModel().getSelectedIndex() == 1 && cmbbox_interval.getSelectionModel().getSelectedIndex() == 5 ))
	    		{
	    			//---TO-DO-- Make a notification that the interval surpasses the range
	    			
	    		}
	    		else
	    		{
	    		
	    		String range = cmbbox_range.getValue();
	    		String interval = cmbbox_interval.getValue();
	    		String stock_code = "";
	    		
		    	if(rdbtn_BSE.isSelected() == true)
		    	{
		    		
		    		stock_code = arrBSEcodes[cmbbox_list.getSelectionModel().getSelectedIndex()].trim();
		    		
		    	}
		    	else
		    	{
		    		
		    		stock_code = arrNSEcodes[cmbbox_list.getSelectionModel().getSelectedIndex()].trim();
		    		
		    	}
		    	final ConnStockGraph connection = new ConnStockGraph( stock_code , range , interval );
		    	
		    	taskThread = new Thread(new Runnable(){
	
					@Override
					public void run()
					{
												
						try {
								log.debug("Going to sleep for 1 second");
								Thread.sleep(1000);
								log.debug("Starting the connection");
								stockresponse = connection.start_connection();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							 if( stockresponse.contains("You have exceeded the DAILY quota") )
								{
									Platform.runLater(new Runnable() {

										@Override
										public void run() {
											Alert nointernet = new Alert(AlertType.ERROR);
											nointernet.setTitle("Error!");
											nointernet.setHeaderText(null);
											nointernet.setContentText("You have exceeded the DAILY quota for Requests on your current plan, BASIC. Upgrade your plan.");
											nointernet.show();
											
										}

									});				
								}
							
								log.debug("Extracting hex values from the response, and converting it to MMM,YY format, which is the x-axis of the linechart");
								connection.gettime(stockresponse);
								log.debug("Extracting data from the response which is the y-axis of the linechart");
								connection.getdata(stockresponse);
								
						
						
						Platform.runLater(new Runnable() 
						{
		
							@Override
							public void run()
							{	
								if(connection.isGraph_allow())
								{
									log.debug("Hiding the labels and comboboxes");
									log.debug("1.//.......range label");
									lbl_range.setVisible(false);
									log.debug("2.//.......range combobox");
									cmbbox_range.setVisible(false);
									log.debug("3.//.......interval label");
									lbl_interval.setVisible(false);
									log.debug("4.//.......interval combobox");
									cmbbox_interval.setVisible(false);
									log.debug("Exposing scrollpane and line chart");
									Scroll.setVisible(true);
									StockChart.setVisible(true);
									log.debug("Setting the title of the linechart");
									StockChart.setTitle(cmbbox_list.getValue().trim());
									log.debug("Setting the value in the chart");
									
									StockChart.setDisable(false);
									XYChart.Series<String,Double> Stockseries = new XYChart.Series<String,Double>();
									//XYChart.Data<String , Double> Stockdata =new XYChart.Data<String , Double>();
									Scroll.setHvalue(0);
									String legend = "";
									if(cmbbox_range.getValue().trim() == "1d")
									{
										
										legend = "The range is a day with an interval of "+cmbbox_interval.getValue().trim()+" on "+connection.date_dtmnthyr.get(0);
										makeStockChart(connection.date_timedt,connection.values,Stockseries,legend);
										
									}
									else if( cmbbox_range.getValue().trim() == "5d" )
									{
										if( ( cmbbox_interval.getValue().trim() == "1m" ) || ( cmbbox_interval.getValue().trim() == "5m" ) || ( cmbbox_interval.getValue().trim() == "15m" ))
										{	
											legend = "The range is of 5d with an interval of "+cmbbox_interval.getValue().trim()+" from "+connection.date_dtmnthyr.get(0)+" to "+connection.date_dtmnthyr.get(connection.date_dtmnthyr.size()-1);
											makeStockChart(connection.date_timedt,connection.values,Stockseries,legend);
											
										}
										else
										{	
											legend = "The range is of 5d with an interval of "+cmbbox_interval.getValue().trim()+" from "+connection.date_dtmnthyr.get(0)+" to "+connection.date_dtmnthyr.get(connection.date_dtmnthyr.size()-1);
											makeStockChart(connection.date_dtmnth,connection.values,Stockseries,legend);
										}
									}
									else if( cmbbox_range.getValue().trim() == "3mo" )
									{
										if( ( cmbbox_interval.getValue().trim() == "1d" ) || ( cmbbox_interval.getValue().trim() == "1wk" ))
										{	
											legend = "The range is of 3mo with an interval of "+cmbbox_interval.getValue().trim()+" from "+connection.date_dtmnthyr.get(0)+" to "+connection.date_dtmnthyr.get(connection.date_dtmnthyr.size()-1);
											makeStockChart(connection.date_dtmnth,connection.values,Stockseries,legend);
										}
										else
										{
											legend = "The range is of 3mo with an interval of "+cmbbox_interval.getValue().trim()+" from "+connection.date_dtmnthyr.get(0)+" to "+connection.date_dtmnthyr.get(connection.date_dtmnthyr.size()-1);
											makeStockChart(connection.date_mnthyr,connection.values,Stockseries,legend);
										}
									}
									else if( cmbbox_range.getValue().trim() == "6mo" )
									{
										if( ( cmbbox_interval.getValue().trim() == "1d" ) || ( cmbbox_interval.getValue().trim() == "1wk" ))
										{	
											legend = "The range is of 6mo with an interval of "+cmbbox_interval.getValue().trim()+" from "+connection.date_dtmnthyr.get(0)+" to "+connection.date_dtmnthyr.get(connection.date_dtmnthyr.size()-1);
											makeStockChart(connection.date_dtmnth,connection.values,Stockseries,legend);
										}
										else
										{	
											legend = "The range is of 6mo with an interval of "+cmbbox_interval.getValue().trim()+" from "+connection.date_dtmnthyr.get(0)+" to "+connection.date_dtmnthyr.get(connection.date_dtmnthyr.size()-1);
											makeStockChart(connection.date_mnthyr,connection.values,Stockseries,legend);
										}
									}
									else
									{
										legend = "The range is of "+cmbbox_range.getValue().trim()+" with an interval of "+cmbbox_interval.getValue().trim()+" from "+connection.date_dtmnthyr.get(0)+" to "+connection.date_dtmnthyr.get(connection.date_dtmnthyr.size()-1);
										makeStockChart(connection.date_dtmnthyr,connection.values,Stockseries,legend);
										
									}
									
									StockChart.setAnimated(false);
									
									//---------This checks the size of the value arraylist and broadens or shortens scroll pane  
									
									if(connection.values.size() <=30)
									{
										StockChart.autosize();
									}
									else if( connection.values.size() > 30 && connection.values.size() <=90)
									{
										Scroll.setMaxWidth(3000);
										Scroll.setMinSize(627, 243);
										StockChart.setMinSize(Scroll.getMaxWidth(), Scroll.getMinHeight()-10);
									}
									else if( connection.values.size() > 90 && connection.values.size() <=300)
									{
										Scroll.setMaxWidth(10000);
										Scroll.setMinSize(627, 243);
										StockChart.setMinSize(Scroll.getMaxWidth(), Scroll.getMinHeight()-10);
									}
									
									else if( connection.values.size()>300 && connection.values.size()<=900)
									{
										Scroll.setMaxWidth(20000);
										Scroll.setMinSize(627, 243);
										StockChart.setMinSize(Scroll.getMaxWidth(), Scroll.getMinHeight()-10);
									}
									else if( connection.values.size()>900 && connection.values.size()<=1500)
									{
										Scroll.setMaxWidth(32000);
										Scroll.setMinSize(627, 243);
										StockChart.setMinSize(Scroll.getMaxWidth(), Scroll.getMinHeight()-10);
									}
									else
									{
										Scroll.setMaxWidth(45000);
										Scroll.setMinSize(627, 243);
										StockChart.setMinSize(Scroll.getMaxWidth(), Scroll.getMinHeight()-10);
									}
										
									StockChart.getData().add(Stockseries);
									StockChart.setCursor(Cursor.CROSSHAIR);
									
									//StockChart.lege
										//Putting a tooltip on each node of the Line Chart
									
							            for (XYChart.Data<String, Double > d : Stockseries.getData()) {
							                Tooltip stockvisqt = new Tooltip();
							                stockvisqt.setText( "Close : \u20B9"+" "+ d.getYValue()+ "\n" + "As Of: "+ d.getXValue().toString() );
							                
							            	Tooltip.install(d.getNode(), stockvisqt);
							            	
							               
							            }
							            StockChart.setClip(null);
								}
							}
							
			      		});
			 		} 
		    		
		    	}); 
		    	taskThread.start();
	    		}	
	    	}
	    	
	    	else
	    	{
	    		log.debug("Error notification stating to feed the key in settings");
	    		Platform.runLater(new Runnable() {

					@Override
					public void run() {
						
						Alert nokey = new Alert(AlertType.ERROR);
						nokey.initModality(Modality.APPLICATION_MODAL);
						nokey.setTitle("Key Required!");
						nokey.setHeaderText(null);
						nokey.setContentText("Please put in a valid API key in settings.");
						nokey.show();
						
						
						
					}
					
				});
	    	}
    } 
}