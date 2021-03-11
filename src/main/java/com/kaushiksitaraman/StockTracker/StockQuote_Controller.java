package com.kaushiksitaraman.StockTracker;

import java.io.IOException;
import java.text.DecimalFormat;
import org.controlsfx.control.textfield.TextFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class StockQuote_Controller {
	
	@FXML
    private Pane pane;
	
    @FXML
    private RadioButton rdbtn_nse;

    @FXML
    private RadioButton rdbtn_bse;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private Label lbl_companyname;

    @FXML
    private Label lbl_marketvalueheader;

    @FXML
    private Label lbl_marketvalue;

    @FXML
    private Label lbl_changeheader;

    @FXML
    private Label lbl_changevalue;

    @FXML
    private Label lbl_highheader;

    @FXML
    private Label lbl_highvalue;

    @FXML
    private Label lbl_lowheader;

    @FXML
    private Label lbl_lowvalue;

    @FXML
    private Label lbl_openheader;

    @FXML
    private Label lbl_openvalue;

    @FXML
    private Label lbl_closeheader;

    @FXML
    private Label lbl_closevalue;

    @FXML
    private Label lbl_marketcapheader;

    @FXML
    private Label lbl_marketcapvalue;

    @FXML
    private Button btn_getquote;

    @FXML
    private Button btn_reset;
    
    private String key_present;

    private static final Logger log = LoggerFactory.getLogger(FirstController.class);	
    
    private FileData dataconn = new FileData();
    
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
    
    private String temp="";
    
    private String theme;
    
   private String secTheme;
   
   private boolean quote_allow = true;
    
    public void initialize() {
    	//-----This is to make sure the reset and Get Quote buttons are disabled till hte selection is made in the combobox----//
    	
    	log.debug("Disabling Get Quote Button, enabled when combobox is filled");
    	btn_getquote.disableProperty().bind(
    			combobox.valueProperty().isNull()
    	);
    	log.debug("Disabling Reset Button, enabled when combobox is filled");
    	btn_reset.disableProperty().bind(
    			combobox.valueProperty().isNull()
    	);
    	
    	log.debug("Checking whether key has been implemented and saved");
    	if(!dataconn.getKey().equals("none"))
    	{

    		key_present = "yes";
    		
    	}
    	else
    	{
    		key_present = "no";
    	}
    	theme = dataconn.getTheme();
    	secTheme = dataconn.getSecTheme();
    	set_style(theme , secTheme);
    }
    
	public void set_style(String theme , String secTheme)
	{	
		rdbtn_bse.getStyleClass().add("rdbtn-"+theme+"-"+secTheme);
		rdbtn_nse.getStyleClass().add("rdbtn-"+theme+"-"+secTheme);
		combobox.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
		lbl_companyname.getStyleClass().clear();
		lbl_companyname.getStyleClass().add("lbl-"+theme);
		lbl_marketvalueheader.getStyleClass().add("lbl-"+theme);
		lbl_marketvalue.getStyleClass().add("lbl-"+theme);
		lbl_changeheader.getStyleClass().add("lbl-"+theme);
		lbl_highheader.getStyleClass().add("lbl-"+theme);
		lbl_highvalue.getStyleClass().add("lbl-"+theme);
		lbl_lowheader.getStyleClass().add("lbl-"+theme);
		lbl_lowvalue.getStyleClass().add("lbl-"+theme);
		lbl_openheader.getStyleClass().add("lbl-"+theme);
		lbl_openvalue.getStyleClass().add("lbl-"+theme);
		lbl_closeheader.getStyleClass().add("lbl-"+theme);
		lbl_closevalue.getStyleClass().add("lbl-"+theme);
		lbl_marketcapheader.getStyleClass().add("lbl-"+theme);
		lbl_marketcapvalue.getStyleClass().add("lbl-"+theme);
		pane.getStyleClass().add("pane-"+theme);
		btn_getquote.getStyleClass().add("btn-"+theme+"-"+secTheme);
		btn_reset.getStyleClass().add("btn-"+theme+"-"+secTheme);
		
	}
    private void Disable()
    {
    	lbl_companyname.setVisible(false);
		lbl_companyname.setDisable(true);
		lbl_companyname.setText("");
		lbl_marketvalueheader.setVisible(false);
		lbl_marketvalueheader.setDisable(true);
		lbl_marketvalue.setVisible(false);
		lbl_marketvalue.setDisable(true);
		lbl_marketvalue.setText("");
		lbl_changeheader.setVisible(false);
		lbl_changeheader.setDisable(true);
		lbl_changevalue.setVisible(false);
		lbl_changevalue.setDisable(true);
		lbl_changevalue.setText("");
		lbl_highheader.setVisible(false);
		lbl_highheader.setDisable(true);
		lbl_highvalue.setVisible(false);
		lbl_highvalue.setDisable(true);
		lbl_highvalue.setText("");
		lbl_lowheader.setVisible(false);
		lbl_lowheader.setDisable(true);
		lbl_lowvalue.setVisible(false);
		lbl_lowvalue.setDisable(true);
		lbl_lowvalue.setText("");
		lbl_openheader.setVisible(false);
		lbl_openheader.setDisable(true);
		lbl_openvalue.setVisible(false);
		lbl_openvalue.setDisable(true);
		lbl_openvalue.setText("");
		lbl_closeheader.setVisible(false);
		lbl_closeheader.setDisable(true);
		lbl_closevalue.setVisible(false);
		lbl_closevalue.setDisable(true);
		lbl_closevalue.setText("");
		lbl_marketcapheader.setVisible(false);
		lbl_marketcapheader.setDisable(true);
		lbl_marketcapvalue.setVisible(false);
		lbl_marketcapvalue.setDisable(true);
		lbl_marketcapvalue.setText("");
    }
    
    private void Enable()
    {
    	lbl_companyname.setVisible(true);
		lbl_companyname.setDisable(false);
		lbl_marketvalueheader.setVisible(true);
		lbl_marketvalueheader.setDisable(false);
		lbl_marketvalue.setVisible(true);
		lbl_marketvalue.setDisable(false);
		lbl_changeheader.setVisible(true);
		lbl_changeheader.setDisable(false);
		lbl_changevalue.setVisible(true);
		lbl_changevalue.setDisable(false);
		lbl_highheader.setVisible(true);
		lbl_highheader.setDisable(false);
		lbl_highvalue.setVisible(true);
		lbl_highvalue.setDisable(false);
		lbl_lowheader.setVisible(true);
		lbl_lowheader.setDisable(false);
		lbl_lowvalue.setVisible(true);
		lbl_lowvalue.setDisable(false);
		lbl_openheader.setVisible(true);
		lbl_openheader.setDisable(false);
		lbl_openvalue.setVisible(true);
		lbl_openvalue.setDisable(false);
		lbl_closeheader.setVisible(true);
		lbl_closeheader.setDisable(false);
		lbl_closevalue.setVisible(true);
		lbl_closevalue.setDisable(false);
		lbl_marketcapheader.setVisible(true);
		lbl_marketcapheader.setDisable(false);
		lbl_marketcapvalue.setVisible(true);
		lbl_marketcapvalue.setDisable(false);
    }

   
    @FXML
    void Select_Bse(ActionEvent event) {
    	
    	if(rdbtn_bse.isSelected()== true)
    	{
    		log.debug("Deselecting NSE");
    		rdbtn_nse.setSelected(false);
    		log.debug("Hiding NSE radiobutton");
    		rdbtn_nse.setVisible(false);
    		log.debug("relocating bse radiobutton so as to get it in center");
    		rdbtn_bse.relocate(284, 32);
    		log.debug("relocating NSE radiobutton to its usual position as a failsafe");
    		rdbtn_nse.relocate(336, 32);
    		log.debug("Putting BSE values in combobox and enabling it");
    		ObservableList<String> bsedata = FXCollections.observableArrayList(arrBSE);
			combobox.setItems(bsedata);
			combobox.setDisable(false);
			combobox.setPromptText("Select the BSE Stock");
			TextFields.bindAutoCompletion(combobox.getEditor(), combobox.getItems());
			
    	}
    	else
    	{
    		log.debug("Deselecting NSE");
    		rdbtn_nse.setSelected(false);
    		log.debug("expose NSE radiobutton");
    		rdbtn_nse.setVisible(true);
    		log.debug("relocating BSE radiobutton to its usual position");
    		rdbtn_bse.relocate(227, 32);
    		log.debug("Disabling and removing the combox entries");
    		combobox.getSelectionModel().clearSelection();
    		combobox.getItems().clear();
			combobox.setDisable(true);
			combobox.setPromptText("Select BSE or NSE");
			log.debug("Disabling all the labels as a fail safe");
			Disable();
    	}
    }

    @FXML
    void Select_Nse(ActionEvent event) {
    	
    	if(rdbtn_nse.isSelected()== true)
		{
			log.debug("Deselecting BSE");
			rdbtn_bse.setSelected(false);
			log.debug("Hiding BSE radiobutton");
			rdbtn_bse.setVisible(false);
			log.debug("relocating NSE radiobutton so as to get it in center");
			rdbtn_nse.relocate(284 , 32);
			log.debug("relocating BSE radiobutton to its usual position as a failsafe");
			rdbtn_bse.relocate(227, 32);
			log.debug("Putting NSE values in combobox and enabling it");
			ObservableList<String> nsedata = FXCollections.observableArrayList(arrNSE);
			combobox.setItems(nsedata);
			combobox.setDisable(false);
			combobox.setPromptText("Select the NSE Stock");
			TextFields.bindAutoCompletion(combobox.getEditor(), combobox.getItems());
			
    	}
		else
		{
			log.debug("Deselecting BSE");
			rdbtn_bse.setSelected(false);
			log.debug("expose BSE radiobutton");
			rdbtn_bse.setVisible(true);
			log.debug("relocating NSE radiobutton to its usual position");
			rdbtn_nse.relocate(336, 32);
			log.debug("Disabling and removing the combox entries");
			combobox.getSelectionModel().clearSelection();
    		combobox.getItems().clear();
			combobox.setDisable(true);
			combobox.setPromptText("Select BSE or NSE");
			log.debug("Disabling all the labels as a fail safe");
			Disable();
			
		}
    	
    }

    @FXML
    void reset(ActionEvent event) {

    	log.debug("resetting the rdbtn bse value to false");
    	rdbtn_bse.setVisible(true);
    	log.debug("resetting the rdbtn bse selected value to false");
    	rdbtn_bse.setSelected(false);
    	log.debug("relocating rdbtn bse");
    	rdbtn_bse.relocate(227, 32);
    	log.debug("resetting the rdbtn nse value to false");
    	rdbtn_nse.setVisible(true);
    	log.debug("resetting the rdbtn nse selected value to false");
    	rdbtn_nse.setSelected(false);
    	log.debug("relocating rdbtn nse");
    	rdbtn_nse.relocate(336, 32);
    	log.debug("Disabling and removing the combox entries");
    	combobox.getSelectionModel().clearSelection();
		combobox.getItems().clear();
		combobox.setDisable(true);
		combobox.setPromptText("Select BSE or NSE");
		log.debug("Disabling all the labels");
		Disable();
    	
    }
    
    @FXML
    void Get_Quote(ActionEvent event) {

    	final ConnStockQuote connection = new ConnStockQuote();
    	if(key_present.equals("yes"))
    	{
	    	if(rdbtn_bse.isSelected() == true)
	    		{
	    			connection.setstock(arrBSEcodes[combobox.getSelectionModel().getSelectedIndex()].toString());
	    		}
	    		else
	    		{
	    			connection.setstock(arrNSEcodes[combobox.getSelectionModel().getSelectedIndex()].toString());
	    		}
	    		
	    		 Thread taskThread = new Thread(new Runnable() {
	    			 
					public void run() {
						// TODO Auto-generated method stub
						try {
							log.debug("Going to sleep for a second");
							Thread.sleep(1000);
							log.debug("Getting the connection started.");
							temp = connection.get_connection();
							if( temp.contains("You have exceeded the DAILY quota") )
							{
								Platform.runLater(new Runnable() {

									@Override
									public void run() {
										quote_allow = false;
										Alert nointernet = new Alert(AlertType.ERROR);
										nointernet.setTitle("Error!");
										nointernet.setHeaderText(null);
										nointernet.setContentText("You have exceeded the DAILY quota for Requests on your current plan.");
										nointernet.show();
										
									}

								});				
							}
							log.debug("Going to sleep for a second again");
							Thread.sleep(1000);
							
						}
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
					Platform.runLater(new Runnable() {
						public void run() {
							if(quote_allow)
							{
									String pattern = "##,##,##,###.##";
									DecimalFormat decimalFormat = new DecimalFormat(pattern);
			
									log.debug("Getting the values from the response");
									connection.getdata(temp);
									log.debug("Making all the labels visible and enabling them");
									Enable();
									log.debug("Filling values in GUI");
									
									log.debug("Changing the colour according to change");
									if(connection.values.get(1).charAt(0) != '-')
							    	{
							    		lbl_changevalue.setStyle("-fx-background-color: #008745;-fx-text-fill:white;-fx-border-color: #232323;");
							    	}
							    	else
							    	{	
							    		lbl_changevalue.setStyle("-fx-background-color: #E7202B; -fx-text-fill:black; -fx-border-color: #232323;");
							    	}
									
									log.debug("Putting the values in the labels");
									lbl_changevalue.setText(decimalFormat.format(Float.valueOf(connection.values.get(0)))+"("+decimalFormat.format(Float.valueOf(connection.values.get(1)))+"%)");
									lbl_marketvalue.setText("\u20B9"+" "+decimalFormat.format(Float.valueOf(connection.values.get(2))));
									lbl_marketvalue.setStyle("-fx-border-color: #232323;");
									lbl_highvalue.setText("\u20B9"+" "+decimalFormat.format(Float.valueOf(connection.values.get(3))));
									lbl_highvalue.setStyle("-fx-border-color: #232323;");
									lbl_lowvalue.setText("\u20B9"+" "+decimalFormat.format(Float.valueOf(connection.values.get(4))));
									lbl_lowvalue.setStyle("-fx-border-color: #232323;");
									lbl_openvalue.setText("\u20B9"+" "+decimalFormat.format(Float.valueOf(connection.values.get(5))));
									lbl_openvalue.setStyle("-fx-border-color: #232323;");
									lbl_closevalue.setText("\u20B9"+" "+decimalFormat.format(Float.valueOf(connection.values.get(6))));
									lbl_closevalue.setStyle("-fx-border-color: #232323;");
									if(combobox.getSelectionModel().getSelectedIndex() != 0)
									{
										lbl_marketcapvalue.setText("\u20B9"+" "+decimalFormat.format(Float.valueOf(connection.values.get(7))));
										lbl_marketcapvalue.setStyle("-fx-border-color: #232323;");
									}
									else
									{
										lbl_marketcapvalue.setText("N/A");
										lbl_marketcapvalue.setDisable(true);
										lbl_marketcapvalue.setStyle("-fx-border-color: #232323;");
									}
									String swap = "";
									for ( int i = 0 ; i < combobox.getValue().length() ; i++)
									{	
										if(combobox.getValue().charAt(i) != '(')
										{
											swap = swap+combobox.getValue().charAt(i);
										}
										else
										{
											break;
										}
									}
									
									lbl_companyname.setText(swap);
									lbl_companyname.setStyle("-fx-font-family: Arial;");	
								}
						}
					});
					
				  }
					
	    	   });
	    		 log.debug("Starting the Thread");
	    		taskThread.start();
	    	}
	    	
    	
    	else
    	{
    		//--TO-DO---
    		log.debug("Implement error notification stating to feed the key in settings");
    		Platform.runLater(new Runnable() {

				@Override
				public void run() {
					
					Alert nokey = new Alert(AlertType.ERROR);
					nokey.setTitle("Key Required!");
					nokey.setHeaderText(null);
					nokey.setContentText("Please put in a valid API key in settings.");
					nokey.show();
				}
			});
    	}
    }
}
