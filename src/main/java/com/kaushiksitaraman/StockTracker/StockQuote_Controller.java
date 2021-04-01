package com.kaushiksitaraman.StockTracker;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.controlsfx.control.textfield.TextFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
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
    
    private String[] arrBSE;
    
    private String[] arrBSEcodes;
      
    private String[] arrNSE;
    
    private String[] arrNSEcodes;
    
    private String temp="";
    
    private String theme;
    
   private String secTheme;
   
   private boolean no_error = true;
    
    
    public void initialize() {
    	
    	log.debug("Setting the value from the database");
    	dataconn.BSE_topvalue();
        	dataconn.NSE_topvalue();
	    	arrBSE = new String[dataconn.getBse_Names().size()];
	    	set_Array( dataconn.getBse_Names() , arrBSE);
	    	arrBSEcodes = new String[dataconn.getBse_Symbols().size()];
	    	set_Array( dataconn.getBse_Symbols() , arrBSEcodes );
	    	arrNSE = new String[dataconn.getNse_Names().size()];
	    	set_Array(dataconn.getNse_Names() , arrNSE );
	    	arrNSEcodes = new String[dataconn.getNse_Symbols().size()];
	    	set_Array( dataconn.getNse_Symbols() , arrNSEcodes );
    	//-----This is to make sure the reset and Get Quote buttons are disabled till the selection is made in the combobox----//
    	
    	log.debug("Disabling Get Quote Button, enabled when combobox is filled");
    	btn_getquote.disableProperty().bind(
    			combobox.valueProperty().isNull()
    	);
    	log.debug("Disabling Reset Button, enabled when combobox is filled");
    	btn_reset.disableProperty().bind(
    			combobox.valueProperty().isNull()
    	);
    	combobox.disableProperty().bind(rdbtn_bse.selectedProperty().not().and(rdbtn_nse.selectedProperty().not()));
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
    
    private void set_Array( ArrayList<String> file , String[] array  )
    {
    	
    	for( int i = 0 ; i < file.size() ; i++ )
    	{
    		
    		array[i] = file.get(i);
    		
    	}
    	
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
							if(temp.equals("IOException")) {
								
								Platform.runLater(new Runnable() {

									@Override
									public void run() {
										
										Alert nointernet = new Alert(AlertType.ERROR);
										nointernet.setTitle("Error!");
										nointernet.setHeaderText(null);
										nointernet.setContentText("Please check your Internet Connection");
										ButtonType Ok = new ButtonType("Okay");
										DialogPane dialogPane = nointernet.getDialogPane();
										nointernet.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
										nointernet.getButtonTypes().setAll( Ok );
										Node Okay = nointernet.getDialogPane().lookupButton(Ok);
										Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
										dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
										nointernet.show();
										no_error = false;
										combobox.getSelectionModel().select(-1);
										log.debug("Deselecting BSE");
										rdbtn_bse.setSelected(false);
										log.debug("expose BSE radiobutton");
										rdbtn_bse.setVisible(true);
										log.debug("relocating NSE radiobutton to its usual position");
										rdbtn_nse.relocate(336, 32);
										log.debug("Disabling and removing the combox entries");
										log.debug("Deselecting NSE");
							    		rdbtn_nse.setSelected(false);
							    		log.debug("expose NSE radiobutton");
							    		rdbtn_nse.setVisible(true);
							    		log.debug("relocating BSE radiobutton to its usual position");
							    		rdbtn_bse.relocate(227, 32);
							    		combobox.setPromptText("Select BSE or NSE");
									}

								});				
								
							}
							else if( temp.contains("You have exceeded the DAILY quota") )
							{
								Platform.runLater(new Runnable() {

									@Override
									public void run() {
										
										Alert over_quote = new Alert(AlertType.ERROR);
										over_quote.setTitle("Error!");
										over_quote.setHeaderText(null);
										over_quote.setContentText("You have exceeded the DAILY quota for Requests on your current plan.");
										ButtonType Ok = new ButtonType("Okay");
										DialogPane dialogPane = over_quote.getDialogPane();
										over_quote.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
										over_quote.getButtonTypes().setAll( Ok );
										Node Okay = over_quote.getDialogPane().lookupButton(Ok);
										Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
										dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
										over_quote.show();
										no_error = false;
										combobox.getSelectionModel().select(-1);
										log.debug("Deselecting BSE");
										rdbtn_bse.setSelected(false);
										log.debug("expose BSE radiobutton");
										rdbtn_bse.setVisible(true);
										log.debug("relocating NSE radiobutton to its usual position");
										rdbtn_nse.relocate(336, 32);
										log.debug("Disabling and removing the combox entries");
										log.debug("Deselecting NSE");
							    		rdbtn_nse.setSelected(false);
							    		log.debug("expose NSE radiobutton");
							    		rdbtn_nse.setVisible(true);
							    		log.debug("relocating BSE radiobutton to its usual position");
							    		rdbtn_bse.relocate(227, 32);
							    		combobox.setPromptText("Select BSE or NSE");
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
							if(no_error)
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
									lbl_marketvalue.setText(decimalFormat.format(Float.valueOf(connection.values.get(2))));
									lbl_marketvalue.setStyle("-fx-border-color: #232323;");
									lbl_highvalue.setText(decimalFormat.format(Float.valueOf(connection.values.get(3))));
									lbl_highvalue.setStyle("-fx-border-color: #232323;");
									lbl_lowvalue.setText(decimalFormat.format(Float.valueOf(connection.values.get(4))));
									lbl_lowvalue.setStyle("-fx-border-color: #232323;");
									lbl_openvalue.setText(decimalFormat.format(Float.valueOf(connection.values.get(5))));
									lbl_openvalue.setStyle("-fx-border-color: #232323;");
									lbl_closevalue.setText(decimalFormat.format(Float.valueOf(connection.values.get(6))));
									lbl_closevalue.setStyle("-fx-border-color: #232323;");
									if(combobox.getSelectionModel().getSelectedIndex() != 0)
									{
										lbl_marketcapvalue.setText(decimalFormat.format(Float.valueOf(connection.values.get(7))));
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
					ButtonType Ok = new ButtonType("Okay");
					DialogPane dialogPane = nokey.getDialogPane();
					nokey.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
					nokey.getButtonTypes().setAll(Ok);
					Node Okay = nokey.getDialogPane().lookupButton(Ok);
					Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
					dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
				}
			});
    	}
    }
}
