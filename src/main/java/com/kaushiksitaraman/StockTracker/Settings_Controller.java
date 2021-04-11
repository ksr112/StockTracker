package com.kaushiksitaraman.StockTracker;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jfoenix.controls.JFXToggleButton;
import java.awt.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Settings_Controller {
	
	@FXML
    private AnchorPane anchor_settings;
	
	@FXML
	private ScrollPane scroll;

	@FXML
    private JFXToggleButton themeswitch;
    
    @FXML
    private VBox Vbox;
    
    @FXML
    private Label lbl1;
    
    @FXML
    private TextField txt_key;
    
    @FXML
    private Button btn_keydelete;
    
    @FXML
    private ImageView img_delete;
    
    @FXML
    private ImageView img_keyresult;
    
    @FXML
    private Label lbl_keynotif;
    
    @FXML
    private Label lbl2;
    
    @FXML
    private Hyperlink link;
    
    @FXML
    private Label lbl3;
    
    @FXML
    private Label lbl4;
         
    @FXML
    private ComboBox<String> cmbbox_sectheme;
    
    @FXML
    private Button btn_setkey;
    
    @FXML
    private Hyperlink link2;

    @FXML
    private CheckBox chkbox_bse;

    @FXML
    private CheckBox chkbox_nse;
    
    @FXML
    private Label lbl_tickers;

    @FXML
    private Label lbl_theme;
    
    @FXML
    private Label lbl5;
    
    @FXML
    private Label lbl_warning;
        
    @FXML
    private Label lbl6;
    
    @FXML
    private Label lbl7;
    
    @FXML
    private Label lbl_ticker;
    
    @FXML
    private Label lbl_font;

    @FXML
    private ComboBox<String> cmbbox_font;

    @FXML
    private Label lbl_speed;

    @FXML
    private Button btn_addremove;
    
    @FXML
    private ComboBox<String> cmbbox_speed;

    private static int tickerbse_counter = 0;
    
    private static int tickernse_counter = 0;

    private static final Logger log = LoggerFactory.getLogger(FirstController.class);
    
    private String theme;
    
    private String key;
    
    private String secTheme;
    
    private String resp_result="";
    
    private String[] secThemevalues  = {"Lime","Purple","Turquoise","Blue","Orange"};
    
    private String[] fontvalues = { "Small" , "Medium" , "Large" };
    
    private String[] speedvalues = { "Slow" , "Normal" , "Fast" };
    
    private boolean not_over_quota = true;
    
   FirstController controller;
   
   Stage bse_stage;
   
   Stage nse_stage;
   
   FileData dataconn = new  FileData();
   
   TickerBSE_Controller bseticker;
   
   TickerNSE_Controller nseticker;
   
   EntriesDB entrydb = new EntriesDB();
   
   DoubleProperty screen_height = new SimpleDoubleProperty();
   
   
  // ----------- This sets the Parent controller ( First Controller ), this is for the secondary theme.
  public void setParent(FirstController controller) {
        this.controller = controller;
    }

    
    @FXML
    private void initialize()
    {	
    	
    		log.debug("Putting the values in the Secondary Theme combobox");
    	    ObservableList<String> ol = FXCollections.observableArrayList(secThemevalues);
    	    cmbbox_sectheme.setItems(ol);
    	    
    	    log.debug("Putting the values in the Font combobox");
    	    ObservableList<String> ol2 = FXCollections.observableArrayList(fontvalues);
    	    cmbbox_font.setItems(ol2);

    	    
    	    log.debug("Putting the values in the Speed combobox");
    	    ObservableList<String> ol3 = FXCollections.observableArrayList(speedvalues);
    	    cmbbox_speed.setItems(ol3);
    	    
    	    //Setting the screen bounds to a an integer property
    	    
    	    screen_height.setValue(Screen.getPrimary().getVisualBounds().getMaxY());
    	    
    	    if(dataconn.getBseAlive_cntr().equals("true"))
           	 {
           		 
           		 chkbox_bse.setSelected(true);
           		 tickerbse_counter = 1;
           	 }
           	 else
           	 {
           		 chkbox_bse.setSelected(false);
           		 tickerbse_counter = 0;
           	 }
    	    if(dataconn.getNseAlive_cntr().equals("true"))
          	 {
          		 
          		 chkbox_nse.setSelected(true);
          		 tickernse_counter = 1;
          	 }
          	 else
          	 {
          		 chkbox_nse.setSelected(false);
          		 tickernse_counter = 0;
          	 }
    	     theme = dataconn.getTheme();
           	 secTheme = dataconn.getSecTheme();
           	 key = dataconn.getKey();
           	 switch(dataconn.getFont())
	            {
	            	case "Small":  cmbbox_font.getSelectionModel().select(0); 
	            				   break;
	            	case "Medium":  cmbbox_font.getSelectionModel().select(1);
	            					break;
	            	case "Large":  cmbbox_font.getSelectionModel().select(2);
	            				   break;
	            }
	            
	            switch(dataconn.getSpeed())
	            {
					case "Slow":  cmbbox_speed.getSelectionModel().select(0); 
								   break;
					case "Normal":  cmbbox_speed.getSelectionModel().select(1);
									break;
					case "Fast":  cmbbox_speed.getSelectionModel().select(2);
					   			   break;
	            }
	        	
 
   
    	
  
    
    if(theme.equals("dark"))
    	{	
    		log.debug("Changing the color of the screen");
    		themeswitch.setSelected(true);
    	
    	}
    	else
    	{	log.debug("Changing the color of the screen");
    		themeswitch.setSelected(false);
    	}
    	if( !key.equals("none"))
    	{
    		lbl_keynotif.setVisible(true);
    		lbl_keynotif.setText("Key Present!");
    	}
    	else
    	{
    		lbl_keynotif.setVisible(false);

    	}
    	log.debug("Setting secondary theme to the elements");
    	
    	switch(secTheme)
    	{	
    		case "Lime" : 	cmbbox_sectheme.getSelectionModel().select(0);
    						break;
    		case "Purple": 	cmbbox_sectheme.getSelectionModel().select(1);
    						break;
    		case "Turquoise":	cmbbox_sectheme.getSelectionModel().select(2);
    							break;
    		case "Blue" : 	cmbbox_sectheme.getSelectionModel().select(3);
    						break;
    		case "Orange": cmbbox_sectheme.getSelectionModel().select(4);
    						break;
    	
    	}
    	
	   	
    		switch_theme(theme , secTheme, 2);
	   		
    		// Binding visible property of delete button to 'Key Present' label
	   
	    btn_keydelete.visibleProperty().bind(lbl_keynotif.visibleProperty());
	    btn_setkey.disableProperty().bind(txt_key.textProperty().isEmpty());
	    btn_addremove.disableProperty().bind(chkbox_bse.selectedProperty().or(chkbox_nse.selectedProperty()).or(lbl_keynotif.visibleProperty().not()));
	    log.debug("Creating an event listener on secondary theme combobox");
	    cmbbox_sectheme.getSelectionModel().selectedItemProperty().addListener(getListener());
	    
	    log.debug("Binding checkboxes to label");
	    chkbox_bse.disableProperty().bind(lbl_keynotif.visibleProperty().not());
	    chkbox_nse.disableProperty().bind(lbl_keynotif.visibleProperty().not());
	    lbl_tickers.disableProperty().bind(lbl_keynotif.visibleProperty().not());						
	
	    log.debug("Binding label and combobox to ticker checkbox, so if ticker is used, it disables these labels and comboboxes");
	    lbl_font.disableProperty().bind(chkbox_bse.selectedProperty().or(chkbox_nse.selectedProperty()).or(lbl_keynotif.visibleProperty().not()));
	    cmbbox_font.disableProperty().bind(chkbox_bse.selectedProperty().or(chkbox_nse.selectedProperty()).or(lbl_keynotif.visibleProperty().not()));
	    lbl_speed.disableProperty().bind(chkbox_bse.selectedProperty().or(chkbox_nse.selectedProperty()).or(lbl_keynotif.visibleProperty().not()));
	    cmbbox_speed.disableProperty().bind(chkbox_bse.selectedProperty().or(chkbox_nse.selectedProperty()).or(lbl_keynotif.visibleProperty().not()));
	    log.debug("Binding the warrning ticker labels, so when a valid key is entered this label will show up" );
	    lbl_warning.visibleProperty().bind(lbl_keynotif.visibleProperty());
	    lbl5.visibleProperty().bind(lbl_keynotif.visibleProperty());
	    lbl6.visibleProperty().bind(lbl_keynotif.visibleProperty());
	    lbl7.disableProperty().bind(chkbox_bse.selectedProperty().or(chkbox_nse.selectedProperty()).or(lbl_keynotif.visibleProperty().not()));
	    link2.visibleProperty().bind(lbl_keynotif.visibleProperty());
	    // Add listener to the screenbound
	    final ChangeListener<Number> changeListener = new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue,
	            Number newValue) {
	        	Platform.runLater(new Runnable() {

					@Override
					public void run() {
						double offset_value = 0.0;
						double BSE_stageheight = dataconn.getStageheight_BSE();
						Stage BSE_stage = dataconn.getBse_Stage();
						double NSE_stageheight = dataconn.getStageheight_NSE();
						Stage NSE_stage = dataconn.getNse_Stage();
						Double old_value = (Double)oldValue;
						Double new_value = (Double)newValue;
						if(BSE_stage != null && NSE_stage != null)
						{
							if( new_value > old_value)
							{
								offset_value = new_value - old_value;
								
								BSE_stage.setY( BSE_stageheight + offset_value );
								dataconn.setStageheight_BSE( BSE_stageheight + offset_value );
								NSE_stage.setY( NSE_stageheight + offset_value );		
								dataconn.setStageheight_NSE( NSE_stageheight + offset_value );
							}
							else 
							{	
								offset_value = old_value - new_value;
								BSE_stage.setY( BSE_stageheight - offset_value );
								dataconn.setStageheight_BSE( BSE_stageheight - offset_value );
								NSE_stage.setY( NSE_stageheight - offset_value);
								dataconn.setStageheight_NSE( NSE_stageheight - offset_value );
							}
						}
						else
						{
							if(BSE_stage != null)
							{
								if( new_value > old_value)
								{
									offset_value = new_value - old_value;
									BSE_stage.setY( BSE_stageheight + offset_value );
									dataconn.setStageheight_BSE( BSE_stageheight + offset_value );
								}
								else 
								{	
									offset_value = old_value - new_value;
									BSE_stage.setY( BSE_stageheight - offset_value );
									dataconn.setStageheight_BSE( BSE_stageheight - offset_value );
								}
							}
							else
							{
								if( new_value > old_value)
								{
									offset_value = new_value - old_value;
									NSE_stage.setY( NSE_stageheight + offset_value );			
									dataconn.setStageheight_NSE( NSE_stageheight + offset_value );
								}
								else 
								{	
									offset_value = old_value - new_value;
									NSE_stage.setY( NSE_stageheight - offset_value );
									dataconn.setStageheight_NSE( NSE_stageheight - offset_value );
								}
							}
						}
					}
					
				});
	        	
	        }

		};
	    
	   screen_height.addListener(changeListener);
    }
    
    // --------- Method which changes theme 
    
    public void switch_theme(String theme , String secTheme , int counter)
    {
        	themeswitch.getStyleClass().clear();
            themeswitch.getStyleClass().add("toggle-"+secTheme);
            lbl1.getStyleClass().clear();
            lbl1.getStyleClass().add("lbl-"+theme);
            lbl2.getStyleClass().clear();
            lbl2.getStyleClass().add("lbl-"+theme);
            lbl3.getStyleClass().clear();
            lbl3.getStyleClass().add("lbl-"+theme);
            lbl4.getStyleClass().clear();
            lbl4.getStyleClass().add("lbl-"+theme);
            lbl5.getStyleClass().clear();
            lbl5.getStyleClass().add("lbl-"+theme);
            lbl6.getStyleClass().clear();
            lbl6.getStyleClass().add("lbl-"+theme);
            lbl7.getStyleClass().clear();
            lbl7.getStyleClass().add("lbl-"+theme);
            lbl_theme.getStyleClass().clear();
            lbl_theme.getStyleClass().add("lbl-"+theme);
            lbl_ticker.getStyleClass().clear();
            lbl_ticker.getStyleClass().add("lbl-"+theme);
            lbl_tickers.getStyleClass().clear();
            lbl_tickers.getStyleClass().add("lbl-"+theme);
            lbl_font.getStyleClass().clear();
            lbl_font.getStyleClass().add("lbl-"+theme);
            lbl_speed.getStyleClass().clear();
            lbl_speed.getStyleClass().add("lbl-"+theme);
            lbl_tickers.getStyleClass().clear();
            lbl_tickers.getStyleClass().add("lbl-"+theme);
            
            if(theme.equals("white"))
            {	 
            	scroll.setStyle("-fx-background-color:white");
            	Vbox.setStyle("-fx-background-color:white;");
            }
            else
            {	
            	 
		       	scroll.setStyle("-fx-background-color:#232323");
            	Vbox.setStyle("-fx-background-color:#232323;");
            }
    		link.getStyleClass().clear();
            link.getStyleClass().add("link-"+secTheme);
            link2.getStyleClass().clear();
            link2.getStyleClass().add("link-"+secTheme);
            btn_addremove.getStyleClass().clear();
            btn_addremove.getStyleClass().add("btn-"+theme+"-"+secTheme);
			btn_setkey.getStyleClass().clear();
            btn_setkey.getStyleClass().add("btn-"+theme+"-"+secTheme);
        	themeswitch.getStyleClass().clear();
            themeswitch.getStyleClass().add("toggle-"+secTheme);
            cmbbox_sectheme.getStyleClass().clear();
    		cmbbox_sectheme.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
    		
//    		chkbox_bse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
//    		
//
//    		chkbox_nse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
    		
    	
    		if( counter == 1 )
    		{
    			cmbbox_font.getStyleClass().clear();
        		cmbbox_font.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
        		cmbbox_speed.getStyleClass().clear();
        		cmbbox_speed.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
    			if(theme.equals("dark"))
    			{
    			 chkbox_nse.getStyleClass().remove("chkbox-white-"+secTheme);
    			 
   		       	 chkbox_nse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
   		       	 chkbox_bse.getStyleClass().remove("chkbox-white-"+secTheme);
   		       	 chkbox_bse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
    			}
    			else
    			{
    			 chkbox_nse.getStyleClass().remove("chkbox-dark-"+secTheme);
   		       	 chkbox_nse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
   		       	 chkbox_bse.getStyleClass().remove("chkbox-dark-"+secTheme);
   		       	 chkbox_bse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
    			}
    		}
    		if( counter == 2 )
    		{	
    			cmbbox_font.getStyleClass().remove(0);
    			cmbbox_font.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
    			cmbbox_speed.getStyleClass().remove(0);
    			cmbbox_speed.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
    			chkbox_nse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
   		       	chkbox_bse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
    			
    		}
    		if( counter == 3 )
    		{
    			cmbbox_font.getStyleClass().clear();
        		cmbbox_font.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
        		cmbbox_speed.getStyleClass().clear();
        		cmbbox_speed.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
    			chkbox_nse.getStyleClass().remove("chkbox-"+theme+"-"+secTheme);
   		       	chkbox_nse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
   		       	chkbox_bse.getStyleClass().remove("chkbox-"+theme+"-"+secTheme);
   		       	chkbox_bse.getStyleClass().add("chkbox-"+theme+"-"+secTheme);
    		}
    		
    }
    
    
    
    
    
  //----------Action Listener for the combobox so whenever it's value is changed, it changes the theme according to the 
    //----------value provided ( or the index selected ) 

    private ChangeListener<? super String> listener = (observable, oldValue, newValue) -> {
    		
    	
    	log.debug("Serializing the particular option of the combobox into a file");
    	 dataconn.setSecTheme(newValue);
    	 secTheme = newValue;
    	 log.debug("Css Implementation");
//    	 switch_theme(theme , secTheme);
    	 if(!key.equals("none")) 
    	 {
    		 
	    	 chkbox_nse.getStyleClass().remove("chkbox-"+theme+"-"+oldValue);
	    	 chkbox_nse.getStyleClass().add("chkbox-"+theme+"-"+newValue);
	     	 chkbox_bse.getStyleClass().remove("chkbox-"+theme+"-"+oldValue);
	    	 chkbox_bse.getStyleClass().add("chkbox-"+theme+"-"+newValue);
	     	 cmbbox_font.getStyleClass().clear();
     		 cmbbox_font.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
     		 cmbbox_speed.getStyleClass().clear();
     		 cmbbox_speed.getStyleClass().add("cmbbox-"+theme+"-"+secTheme);
	    	 
    	 }
    	    		  
            Platform.runLater(() -> {

            	
             	 switch(newValue)
              	{	
              		case "Lime" : 	controller.setmenucol(theme , "Lime"); 
              						controller.removeimg();
              						controller.img_load(newValue);
              						break;
              		case "Purple": 	controller.setmenucol(theme , "Purple");
              						controller.removeimg();
									controller.img_load(newValue);
              						break;
              		case "Turquoise":	controller.setmenucol(theme , "Turquoise");
              							controller.removeimg();
              							controller.img_load(newValue);
              							break;
              		case "Blue" : 	controller.setmenucol(theme , "Blue");
              						controller.removeimg();
              						controller.img_load(newValue);
              						break;
              		case "Orange":  controller.setmenucol(theme , "Orange");
              						controller.removeimg();
              						controller.img_load(newValue);
              						break;
              	
              	}
             	
             	switch_theme(theme , secTheme,0);
            	
            	                
            });
               
       	
    };
    
    private ChangeListener<? super String> getListener() {
        return listener;
    }
    
    //-------------------This links each thing in this pane which is to be linked to its own css value, depending on the primary
    //-------------------and secondary theme..
    
   
    
    
    @FXML
    void Check_key(ActionEvent event) {
    	
    	ConnStockQuote connection = new ConnStockQuote();
    	log.debug("If key value is null, fetching it's value from file");
    	
    		log.debug("1. We save the entered value in txtfield to a variable");
    		key = txt_key.getText().toString();
    		log.debug("2. Clearing the txt Field ");
    		txt_key.setText("");
			log.debug("3. Creating a Thread to check the key and provide notification accordig to the situaton" );
    		Thread s = new Thread(new Runnable() {
    			
    			public void run()
    			{	log.debug("3.1 Now checking whether the inputted value is legit or not, by calling the ConnStockQuote");
    				try {
    					log.debug("3.1.1 Putting the loadingimage in the ImageView  ");
    					img_keyresult.setImage(new Image("/images/Bean_Eater.gif"));
    					log.debug("3.1.2 Sleeping for 3 seconds :-) ");
    					Thread.sleep(3000);
    					log.debug("3.1.3 Creating a dummy stock variable to make a proper handshake with rapidAPAI");
    					connection.setstock("TCS.NS");
    					log.debug("3.1.4 Connecting to the Yahoo Low Latency RapidAPI and saving the result to a variable");
						resp_result = connection.get_connection(key , "TCS.NS");
						log.debug("3.1.5 Removing the dummy variable from existence");
						connection.defstock();
    				}catch(UnknownHostException e)
    				{
    					
    				}
    				catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
    				
    				Platform.runLater(new Runnable() {
    					public void run() {
    						log.debug("3.1.6 Now checking whether the response contains these words");
    						if(resp_result.contains("subscribed") || resp_result.contains("IOException") || resp_result.contains("You have exceeded the DAILY quota"))
    			    		{	log.debug("3.1.7 We Inside the 'if' that has the failed handshake code");
    			    			if(! resp_result.contains("You have exceeded the DAILY quota"))
    			    			{
    			    				log.debug("3.1.8 Resetting the key variable");
        			    			key = "";
    			    			}
    			    			log.debug("3.1.9 We start a thread to remove the loading image and load a cross img on a timer and also provide the user with a notification of the key being invalid ");
    							Thread t = new Thread(new Runnable() {
    								
    								@Override
    								public void run() {
    									log.debug("3.1.9.1 We remove the loading image ");
    									img_keyresult.setImage(null);
    									
    									
    								
    								
    								Platform.runLater(new Runnable() {
    									public void run()
    									{	//----TO-DO-- Put  a notification which will state that he/she is not subscribed to the API...
    										log.debug("3.1.9.4 Checking if the notification text 'Key Added' is present or not");
    										if(!lbl_keynotif.isVisible())
    										{	if(resp_result.contains("You have exceeded the DAILY quota"))
	    										{
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
	    											dataconn.setKey(key);
	    											img_keyresult.setImage(null);
	    											try {
	    												Thread.sleep(2000);
	    											}
	    											catch(InterruptedException e)
	    											{
	    												e.printStackTrace();
	    											}
	    											log.debug("3.1.10.2 Setting the tick image ");
	    											img_keyresult.setImage(new Image("/images/checkmark.png"));
	    											not_over_quota = false;
	    											log.debug("3.1.10.5 Removing the tick from view");
	    											img_keyresult.setImage(null);
	    											log.debug("3.1.10.6 Setting a green notification stating 'a key is added'");
	    											lbl_keynotif.setVisible(true);
	    											
	    											lbl_keynotif.setStyle("-fx-text-fill:green");
	    											lbl_keynotif.setText("Key Present!");
	    											switch_theme(theme , secTheme,3);
    											
	    										}
	    										else if(resp_result.equals("IOException"))
	    										{
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
	    											
	    										}
	    										if(not_over_quota)
	    										{	
	    											try {
	    	    										log.debug("3.1.9.2 Setting the cross image");
	    	    										img_keyresult.setImage(new Image("/images/delete.png"));
	    	    										log.debug("3.1.9.3 Sleeping for 3 seconds :-) ");
	    	    										Thread.sleep(3000);

	    	    									}	catch (InterruptedException e) {
	    	    										// TODO Auto-generated catch block
	    	    										//e.printStackTrace();
	    	    									}
	    											log.debug("3.1.9.4.1 Notification to the user stating invalid key ");
	        										log.debug("3.1.9.4.2 Now removing the cross image ");
	        										img_keyresult.setImage(null);
	        										lbl_keynotif.setText(null);
	    										}
    										}
    										else
    										{
    											log.debug("3.1.9.4.1 Notification to the user stating invalid key, but there is an approved key present.");
        										log.debug("3.1.9.4.2 Now removing the cross image ");
        										img_keyresult.setImage(null);
        										
    										}
    										
    									 										
    									}
    									
    								});
    								}				
    							});t.start();
    									
    								
    							
    							//img_keyresult.setImage(null);
    							
    			    		}
    			    		else
    			    		{	log.debug("3.1.7 We inside the 'else' part which has the approved handshake code");
    			    			
    			    			 dataconn.setKey(key);
    			    			 log.debug("3.1.10 We start a thread to remove the loading image and load a tick img on a timer and also provide the user with a notification of the key being approved and saved");
    			    	         Thread t = new Thread(new Runnable() {

    									@Override
    									public void run() {
    										// TODO Auto-generated method stub
    										log.debug("3.1.10.1 Removing the loading image from ImageView");
											img_keyresult.setImage(null);
											log.debug("3.1.10.2 Setting the tick image ");
											img_keyresult.setImage(new Image("/images/checkmark.png"));
											log.debug("3.1.10.3 Adding the database ");
											
											
    									
    									
    									Platform.runLater(new Runnable() {
    										public void run()
    										{	//----TO-DO-- Put  a notification which will state that he/she is not subscribed to the API...
    											log.debug("3.1.10.4 Giving notification to the user stating the entered key was approved");
    											
    											log.debug("3.1.10.5 Removing the tick from view");
    											img_keyresult.setImage(null);
    											log.debug("3.1.10.6 Setting a green notification stating 'a key is added'");
    											lbl_keynotif.setVisible(true);
    											
    											lbl_keynotif.setStyle("-fx-text-fill:green");
    											lbl_keynotif.setText("Key Present!");
//    											log.debug("3.1.10.7 Now bringing into view the key delete button");
//    											btn_keydelete.setVisible(true);
    											switch_theme(theme , secTheme,3);
    										}
    										
    									});
    									}				
    								});t.start();
    			    			
    			    		
    			    		
    					
    		    		
    			    		}	
    						
    				}
    				});
    				}
    			});s.start();
			
				
    }
    @FXML
    void delete_key(ActionEvent event) {
    	log.debug("Removing Key Present! notification");
    	lbl_keynotif.setText("");
    	log.debug("Now in place of it we put Key Removing and also adding style");
        lbl_keynotif.setStyle("-fx-text-fill:red");
    	lbl_keynotif.setText("Key Removing...");
    	    	
    	Thread t = new Thread(new Runnable() {
    		
			@Override
			public void run() {
				try {
					log.debug("Sleeping for a second : ) ");
					Thread.sleep(1000);
					log.debug("Looking for the skey.sr file.");
		            dataconn.setKey("none");
		            log.debug("Sleep for 3 seconds : ) ");
		            Thread.sleep(3000);
		           
		           
		        
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
							lbl_keynotif.setText("");
							try {
								log.debug("Sleeping for 2 seconds, just for the fun of it");
								Thread.sleep(2000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							log.debug("Setting the tick image ");
							img_keyresult.setImage(new Image("/images/checkmark.png"));
							log.debug("Putting the final msg of 'Key removed!'");
							lbl_keynotif.setText("Key Removed!");
							
							Thread s = new Thread (new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									
									log.debug("Sleeping for 2 more seconds : ) ");
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									Platform.runLater(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											log.debug("Hiding the key notification label");
											lbl_keynotif.setVisible(false);
											log.debug("Unchecking the bse and nse ticker checkboxes");
											chkbox_bse.setSelected(false);
											chkbox_nse.setSelected(false);
											log.debug("Removing the tick mark");
											img_keyresult.setImage(null);
								            log.debug("We now empty the variable which has the key value");
								            key = "none";
										}
										
									});
								}
								
							});s.start();
							
						 	
				            
						
					}
				
				
				
					
				});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    		
    	});t.start();
   }
    
    @FXML
    void Switch_theme(ActionEvent event) {
    	log.debug("Checking if the toggle is selected and the values is white");
    	if(theme.trim().equals("white"))
    	{	
    		
    			
    			 theme = "dark";
    			 switch_theme(theme , secTheme, 1);
        		 dataconn.setTheme(theme);
        		 Platform.runLater(new Runnable() {
        			 public void run()
        			 {
        				 controller.set_style(theme, secTheme);
        				 controller.removeimg();
        				 controller.img_load(secTheme);
        				 controller.remove_border(theme);
        	    			
        			 }
        		 });
        		     		
    	}
    	else 
    	{
    		
    		
    			 theme = "white";
	    		 switch_theme(theme , secTheme, 1);
	    		 dataconn.setTheme(theme);
	    		 Platform.runLater(new Runnable() {
        			 public void run()
        			 {
        				 controller.set_style(theme, secTheme);
        				 controller.remove_border(theme);
        				 controller.removeimg();
        				 controller.img_load(secTheme);
        				
        			 }
        		 });
	    		 
    		}
    		
    		
    	}
    	
    
    public void uncheck_BSE_tick()
    {
    	chkbox_bse.setSelected(false);
    }
    
    public void uncheck_NSE_tick()
    {
    	chkbox_nse.setSelected(false);
    }
    
    @FXML
    void link_open(ActionEvent event) {

    	log.debug("Starting the system default browser to open the link");
        if(Desktop.isDesktopSupported())
        {	new Thread(() -> {
            try {
                Desktop.getDesktop().browse(new URI("https://rapidapi.com/finance.yahoo.api/api/yahoo-finance-low-latency/details"));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }).start();
        }
        else
        {
        	return;
        }
   
    }
    
    @FXML
    void link_open2(ActionEvent event) {

    	if(Desktop.isDesktopSupported())
        {	new Thread(() -> {
            try {
                Desktop.getDesktop().browse(new URI("https://rapidapi.com/finance.yahoo.api/api/yahoo-finance-low-latency/pricing"));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }).start();
        }
        else
        {
        	return;
        }
    	
    }


    public void start_BSEstage(Rectangle2D primaryScreenBounds, Dimension screenSize, double offset_height) {
        tickerbse_counter = 1;
        this.dataconn.setBseAlive_cntr("true");
        this.dataconn.setFont(((String)this.cmbbox_font.getValue()).trim());
        this.dataconn.setSpeed(((String)this.cmbbox_speed.getValue()).trim());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TickerBSE.fxml"));
        try {
          Parent root1 = fxmlLoader.<Parent>load();
          this.bseticker = fxmlLoader.<TickerBSE_Controller>getController();
          this.bseticker.setParent(this);
          this.dataconn.setBse_ticker(this.bseticker);
          this.bse_stage = new Stage();
          this.bse_stage.initModality(Modality.NONE);
          this.bse_stage.initStyle(StageStyle.UNDECORATED);
          this.bse_stage.resizableProperty().set(false);
          this.bse_stage.setTitle("BSE");
          Scene scene_bse = new Scene(root1);
          this.bse_stage.setScene(scene_bse);
        } catch (IOException e) {
          e.printStackTrace();
        } 
        this.bse_stage.setHeight(30.0D);
        this.bse_stage.setWidth(screenSize.getWidth());
        this.bse_stage.setMaxWidth(screenSize.getWidth());
        this.bse_stage.setMinWidth(screenSize.getWidth());
        this.bse_stage.setX(0.0D);
        double location = primaryScreenBounds.getMaxY() - bse_stage.getHeight() - offset_height; // - insets.bottom
        this.bse_stage.setY(location);
        this.dataconn.setStageheight_BSE(location);
        this.dataconn.setBse_Stage(this.bse_stage);
        this.bse_stage.setAlwaysOnTop(true);
        this.bse_stage.show();
      }
      
      public void start_NSEstage(Rectangle2D primaryScreenBounds, Dimension screenSize, double offset_height) {
        tickernse_counter = 1;
        this.dataconn.setNseAlive_cntr("true");
        this.dataconn.setFont(((String)this.cmbbox_font.getValue()).trim());
        this.dataconn.setSpeed(((String)this.cmbbox_speed.getValue()).trim());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/TickerNSE.fxml"));
        try {
          Parent root2 = fxmlLoader.<Parent>load();
          this.nseticker = fxmlLoader.<TickerNSE_Controller>getController();
          this.nseticker.setParent(this);
          this.dataconn.setNse_ticker(this.nseticker);
          this.nse_stage = new Stage();
          this.nse_stage.initModality(Modality.NONE);
          this.nse_stage.initStyle(StageStyle.UNDECORATED);
          this.nse_stage.resizableProperty().set(false);
          this.nse_stage.setTitle("NSE");
          this.nse_stage.setScene(new Scene(root2));
        } catch (IOException e) {
          e.printStackTrace();
        } 
        nse_stage.setHeight(30.0);
        this.nse_stage.setWidth(screenSize.getWidth());
        this.nse_stage.setMaxWidth(screenSize.getWidth());
        this.nse_stage.setMinWidth(screenSize.getWidth());
        this.nse_stage.setX(0.0D);
        double location = primaryScreenBounds.getMaxY() - nse_stage.getHeight() - offset_height; //- insets.bottom
        this.nse_stage.setY(location);
        this.dataconn.setStageheight_NSE(location);
        this.dataconn.setNse_Stage(this.nse_stage);
        this.nse_stage.setAlwaysOnTop(true);
        this.nse_stage.show();
      }
    
   
      @FXML
      void bsetrackopen(ActionEvent event) throws IOException {
        if (tickerbse_counter == 0) {
          double defaultlocationBSE = 0.0D;
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
          if (this.chkbox_nse.isSelected() == true) {
            Stage tempstage = dataconn.getNse_Stage();
            double stageheightNSE = tempstage.getHeight();
            double stagelocationNSE = this.dataconn.getStageheight_NSE();
            double calculatestageheightBSE = primaryScreenBounds.getMaxY() - nse_stage.getHeight() - stageheightNSE; //- insets.bottom
            if (calculatestageheightBSE == stagelocationNSE) {
              start_BSEstage(primaryScreenBounds, screenSize, defaultlocationBSE);
            } else {
              start_BSEstage(primaryScreenBounds, screenSize, tempstage.getHeight());
            } 
          } else {
            start_BSEstage(primaryScreenBounds, screenSize, defaultlocationBSE);
          } 
        } else {
          tickerbse_counter = 0;
          this.dataconn.setStageheight_BSE(0.0D);
          TickerBSE_Controller bse_ticker = this.dataconn.getBse_ticker();
          bse_ticker.close_scene();
          this.dataconn.setBse_Stage(null);
        } 
      }
      
      @FXML
      void nsetrackopen(ActionEvent event) throws IOException {
        if (tickernse_counter == 0) {
          double defaultlocationNSE = 0.0D;
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
          if (this.chkbox_bse.isSelected() == true) {
            Stage tempstage = this.dataconn.getBse_Stage();
            double stageheightBSE = tempstage.getHeight();
            double stagelocationBSE = this.dataconn.getStageheight_BSE();
            double calculatestageheightNSE = primaryScreenBounds.getMaxY() - bse_stage.getHeight() - stageheightBSE; // - insets.bottom
            if (calculatestageheightNSE == stagelocationBSE) {
              start_NSEstage(primaryScreenBounds, screenSize, defaultlocationNSE);
            } else {
            	
              start_NSEstage(primaryScreenBounds, screenSize, tempstage.getHeight());
            } 
          } else {
            start_NSEstage(primaryScreenBounds, screenSize, defaultlocationNSE);
          } 
        } else {
          tickernse_counter = 0;
          this.dataconn.setStageheight_NSE(0.0D);
          TickerNSE_Controller nse_ticker = this.dataconn.getNse_ticker();
          nse_ticker.close_scene();
          dataconn.setNse_Stage(null);
        } 
      }
    
      
      @FXML
      void add_remove(ActionEvent event) throws IOException {
    	  
    	  Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddRemove.fxml"));
    	  Stage adrem_Stage = new Stage();
          Scene scene = new Scene(root, 444, 512);
          adrem_Stage.initModality(Modality.APPLICATION_MODAL);
          adrem_Stage.initStyle(StageStyle.UNDECORATED);
          adrem_Stage.setAlwaysOnTop(true);
          adrem_Stage.resizableProperty().set(false);
          adrem_Stage.setScene(scene);
          FileData.setAddrem(adrem_Stage);
          adrem_Stage.show();
    	  
      }
    }    



