package com.kaushiksitaraman.StockTracker;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class FirstController {
	
	 	@FXML
	    private Pane first_pane;
	
	 	@FXML
	    private BorderPane borderpane;
	
	 	@FXML
        private Pane pane_topbox;
	 
	 	@FXML
	    private HBox topbox;
	 	
	 	@FXML
	    private Label lbl_key;

	    @FXML
	    private Button btn_key;
	 
	 	@FXML
	    private Pane Changeablepane;
	 
	 	@FXML
	    private ImageView img_quote;
	 
	 	@FXML
	    private ToggleButton btn_Quote;

	 	@FXML
	    private ImageView img_graph;
	 
	    @FXML
	    private ToggleButton btn_Graph;

	    @FXML
	    private ImageView img_settings;
	    
	    @FXML
	    private ToggleButton btn_Settings;
	    
	    @FXML
	    private Pane center_pane;
	    
	    @FXML
	    private HBox hbox_key;
	    
	    @FXML
	    private ImageView img_information;
	    
	    @FXML
	    private ToggleButton btn_quote2;
	    
	    @FXML
	    private ImageView img_quote2;

	    @FXML
	    private ToggleButton btn_graph2;
	    
	    @FXML
	    private ImageView img_graph2;

	    @FXML
	    private ToggleButton btn_ticker;
	    
	    @FXML
	    private ImageView img_ticker;

	    @FXML
	    private ToggleButton btn_theme;
	    
	    @FXML
	    private ImageView img_theme;

	    
	    private FileData data_conn = new FileData();
	    
	    private String theme;
	    
	    private String sectheme; 
	    
	    private static final Logger log = LoggerFactory.getLogger(FirstController.class);
	    
	    
	    public void initialize() {
	    {	
	    	
			File file = new File(".Settings.ser");
	    	log.debug("Checking if File exists");
	    	if(file.exists())
	    	{
	    			data_conn.get_data();
	    			theme = data_conn.getTheme();
	    			sectheme = data_conn.getSecTheme();
	    			if(!data_conn.getKey().equals("none"))
	    			{	
	    				
		    			hbox_key.setDisable(true);
		    			hbox_key.setVisible(false);
	    			}
	    			data_conn.setBseAlive_cntr("false");
		    		data_conn.setNseAlive_cntr("false");
	    			if(theme.equals("white"))
					{	
	    				hbox_key.getStyleClass().add("hbox-white");
	    		    	img_theme.setImage(new Image("/images/Theme-white.png"));
	    				img_quote2.setImage(new Image("/images/bear-dark.png"));
	    				img_graph2.setImage(new Image("/images/bull-dark.png"));
	    				img_ticker.setImage(new Image("/images/Ticker-white.png"));
	    				lbl_key.getStyleClass().add("lbl-white");
	    				btn_quote2.getStyleClass().add("btn-white");
	    				btn_graph2.getStyleClass().add("btn-white");
	    				btn_ticker.getStyleClass().add("btn-white");
	    				btn_theme.getStyleClass().add("btn-white");
	    				btn_theme.setText("Set Dark Theme");
	    				first_pane.getStyleClass().add("white");
					}
					else
					{
						hbox_key.getStyleClass().add("hbox-dark");
				    	img_theme.setImage(new Image("/images/Theme-dark.png"));
						img_quote2.setImage(new Image("/images/bear-white.png"));
	    				img_graph2.setImage(new Image("/images/bull-white.png"));
	    				img_ticker.setImage(new Image("/images/Ticker-dark.png"));
	    				lbl_key.getStyleClass().add("lbl-dark");
	    				btn_quote2.getStyleClass().add("btn-dark");
	    				btn_graph2.getStyleClass().add("btn-dark");
	    				btn_ticker.getStyleClass().add("btn-dark");
	    				btn_theme.getStyleClass().add("btn-dark");
	    				btn_theme.setText("Set Light Theme");
	    				first_pane.getStyleClass().add("dark");
					}
	    			switch(data_conn.getSecTheme())
					{
						case "Lime" :  setmenucol(theme , "Lime");
									   img_information.setImage(new Image("/images/Information-Lime.png"));
									   btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
									   break;
							
						case "Purple" :	setmenucol(theme , "Purple");
										img_information.setImage(new Image("/images/Information-Purple.png"));
										btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
										break; 
							
						case "Turquoise" : setmenucol(theme , "Turquoise");
										   img_information.setImage(new Image("/images/Information-Turquoise.png"));
										   btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
										   break;
							
						case "Blue" : setmenucol(theme , "Blue");
									  img_information.setImage(new Image("/images/Information-Blue.png"));
									  btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
									  break;
							
						case "Orange": setmenucol(theme , "Orange");
									   img_information.setImage(new Image("/images/Information-Orange.png"));
									   btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
									   break;
					}
					img_load(sectheme);
					
	    	}
	    	else
	    	{
	    		data_conn.setTheme("white");
	    		data_conn.setSecTheme("Orange");
	    		data_conn.setKey("none");
	    		data_conn.setBseAlive_cntr("false");
	    		data_conn.setNseAlive_cntr("false");
	    		data_conn.setFont("Medium");
	    		data_conn.setSpeed("Normal");
	    		theme = "white";
	    		sectheme = "Orange";
	    		if(data_conn.getTheme().equals("white"))
				{
	    			hbox_key.getStyleClass().add("hbox-white");
	    	    	img_theme.setImage(new Image("/images/Theme-white.png"));
	    			img_quote2.setImage(new Image("/images/bear-dark.png"));
    				img_graph2.setImage(new Image("/images/bull-dark.png"));
    				img_ticker.setImage(new Image("/images/Ticker-white.png"));
    				lbl_key.getStyleClass().add("lbl-white");
    				btn_quote2.getStyleClass().add("btn-white");
    				btn_graph2.getStyleClass().add("btn-white");
    				btn_ticker.getStyleClass().add("btn-white");
    				btn_theme.getStyleClass().add("btn-white");
    				btn_theme.setText("Set Dark Theme");
    				first_pane.getStyleClass().add("white");
				}
				else
				{
					hbox_key.getStyleClass().add("hbox-dark");
			    	img_theme.setImage(new Image("/images/Theme-dark.png"));
					img_quote2.setImage(new Image("/images/bear-white.png"));
    				img_graph2.setImage(new Image("/images/bull-white.png"));
    				img_ticker.setImage(new Image("/images/Ticker-dark.png"));
    				lbl_key.getStyleClass().add("lbl-dark");
    				btn_quote2.getStyleClass().add("btn-dark");
    				btn_graph2.getStyleClass().add("btn-dark");
    				btn_ticker.getStyleClass().add("btn-dark");
    				btn_theme.getStyleClass().add("btn-dark");
    				btn_theme.setText("Set Light Theme");
    				first_pane.getStyleClass().add("dark");
				}
				switch(data_conn.getSecTheme())
				{
					case "Lime" :  setmenucol(theme , "Lime");
									img_information.setImage(new Image("/images/Information-Lime.png"));
									   btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
									   break;
						
					case "Purple" :	setmenucol(theme , "Purple");
									img_information.setImage(new Image("/images/Information-Purple.png"));
									btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
									break; 
						
					case "Turquoise" : setmenucol(theme , "Turquoise");
										img_information.setImage(new Image("/images/Information-Turquoise.png"));
										   btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
										   break;
						
					case "Blue" : setmenucol(theme , "Blue");
								img_information.setImage(new Image("/images/Information-Blue.png"));
								  btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
								  break;
						
					case "Orange": setmenucol(theme , "Orange");
									img_information.setImage(new Image("/images/Information-Orange.png"));
									   btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
									   break;
				}
				img_load(sectheme);
				set_style(theme,sectheme);
	    		}
	    	}

	    }
	        
	    public void remove_border(String theme)
	    {
	    	switch(theme)
	    	{
	    	case "dark": pane_topbox.getStyleClass().clear();
	    					pane_topbox.getStyleClass().add("dark");
	    					break;
	    					
	    	case "white": pane_topbox.getStyleClass().clear();
	    				  pane_topbox.getStyleClass().add("white");
	    				  break;
	    	}
	    	
	    }
	    
	    public void removeimg()
	    {
	    	img_quote.setImage(null);
	    	img_graph.setImage(null);
	    	img_settings.setImage(null);
	    }
	    
	    public void img_load(String color)
	    {	
	    	img_quote.setImage(new Image("/images/bear-"+color+".png"));
			img_graph.setImage(new Image("/images/bull-"+color+".png"));
			img_settings.setImage(new Image("/images/settings-"+color+".png"));
	    }
	    
	    public void set_style( String theme , String secTheme )
	    {
	    	this.theme = theme; 
	    	btn_Quote.getStyleClass().clear();
	    	btn_Quote.getStyleClass().add("btn-"+theme+"-"+secTheme);
	    	btn_Graph.getStyleClass().clear();
	    	btn_Graph.getStyleClass().add("btn-"+theme+"-"+secTheme);
	    	btn_Settings.getStyleClass().clear();
	    	btn_Settings.getStyleClass().add("btn-"+theme+"-"+secTheme);
	    }
	    
	    public void setmenucol(String theme , String colour)
	 {	
	    	Platform.runLater(() -> {
					this.theme = theme;
					sectheme = colour;
					  set_style(theme, colour);
					
				});
	    		
	    	
		
	 }
	    
	   
	    
    @FXML
    void change_theme(ActionEvent event) {
    	if(theme.equals("white"))
    	{
    		hbox_key.getStyleClass().clear();
    		hbox_key.getStyleClass().add("hbox-dark");
	    	img_theme.setImage(new Image("/images/Theme-dark.png"));
    		img_quote2.setImage(new Image("/images/bear-white.png"));
			img_graph2.setImage(new Image("/images/bull-white.png"));
			img_ticker.setImage(new Image("/images/Ticker-dark.png"));
			lbl_key.getStyleClass().clear();
			lbl_key.getStyleClass().add("lbl-dark");
			btn_quote2.getStyleClass().clear();
			btn_quote2.getStyleClass().add("btn-dark");
			btn_graph2.getStyleClass().clear();
			btn_graph2.getStyleClass().add("btn-dark");
			btn_ticker.getStyleClass().clear();
			btn_ticker.getStyleClass().add("btn-dark");
			btn_theme.getStyleClass().clear();
			btn_theme.getStyleClass().add("btn-dark");
			btn_theme.setText("Set Light Theme");
			first_pane.getStyleClass().clear();
			first_pane.getStyleClass().add("dark");
			data_conn.setTheme("dark");
			theme = "dark";
			btn_key.getStyleClass().clear();
			btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
			set_style(theme,sectheme);
    	}
    	else
    	{
    		hbox_key.getStyleClass().clear();
    		hbox_key.getStyleClass().add("hbox-white");
	    	img_theme.setImage(new Image("/images/Theme-white.png"));
    		img_quote2.setImage(new Image("/images/bear-dark.png"));
			img_graph2.setImage(new Image("/images/bull-dark.png"));
			img_ticker.setImage(new Image("/images/Ticker-white.png"));
			lbl_key.getStyleClass().clear();
			lbl_key.getStyleClass().add("lbl-white");
			btn_quote2.getStyleClass().clear();
			btn_quote2.getStyleClass().add("btn-white");
			btn_graph2.getStyleClass().clear();
			btn_graph2.getStyleClass().add("btn-white");
			btn_ticker.getStyleClass().clear();
			btn_ticker.getStyleClass().add("btn-white");
			btn_theme.getStyleClass().clear();
			btn_theme.getStyleClass().add("btn-white");
			btn_theme.setText("Set Dark Theme");
			first_pane.getStyleClass().clear();
			first_pane.getStyleClass().add("white");
			data_conn.setTheme("white");
			theme = "white";
			btn_key.getStyleClass().clear();
			btn_key.getStyleClass().add("btn2-"+theme+"-"+sectheme);
			set_style(theme,sectheme);
    	}
    }

    @FXML
    void grphmouse_enter(MouseEvent event) {

    	Platform.runLater(new Runnable() {
    		public void run()
    		{
    			switch(theme)
		    	{
					case "white" :  btn_Graph.setStyle("-fx-text-fill:white;-fx-background-insets: 1px 1px 1px 1px rgba(255, 255,255, 1);");
								   img_graph.setImage(null);
								   img_graph.setImage(new Image("/images/bull-white.png"));
					     	   		break;
						
					case "dark" :	btn_Graph.setStyle("-fx-text-fill:#121212;-fx-background-insets: 1px 1px 1px 1px rgba(18, 18, 18, 1);");
									img_graph.setImage(null);
									   img_graph.setImage(new Image("/images/bull-dark.png"));
						  	   		break; 
						
					
		    	}
    		}
    	});
    	
    	
    }

    @FXML
    void grphmouse_exit(MouseEvent event) {
    	
    	Platform.runLater(new Runnable() {
    		public void run()
    		{
    			btn_Graph.setStyle(null) ;
		        set_style(theme, sectheme);
		        img_graph.setImage(null);
				img_graph.setImage(new Image("/images/bull-"+sectheme+".png"));
    		}
    	});
    	
	   	  
    	
    }
    
    @FXML
    void graph2_open(ActionEvent event) {
    	open_graph();
    }
    
    void open_graph()
    {
    	FXML_Switcher ui = new FXML_Switcher();
		Pane temp = ui.getScene("/fxml/StockGraph");
		borderpane.setCenter(temp);
    }
    
	@FXML
    void Graph_Open(ActionEvent event) {
    	
		btn_Graph.setSelected(true);
		open_graph();
    
		
    	
    }
        
	@FXML
    void qtmouse_enter(MouseEvent event) {

		Platform.runLater(new Runnable() {
    		public void run()
    		{
    			switch(theme)
		    	{
					case "dark" :  btn_Quote.setStyle("-fx-text-fill:#121212;-fx-background-insets: 1px 1px 1px 1px rgba(0, 0, 0, 0.24);");
									img_quote.setImage(null);
									img_quote.setImage(new Image("/images/bear-dark.png"));
					     	   		break;
						
					case "white" :	btn_Quote.setStyle("-fx-text-fill:white;-fx-background-insets: 1px 1px 1px 1px rgba(0, 0, 0, 0.24);");
									img_quote.setImage(null);
									img_quote.setImage(new Image("/images/bear-white.png"));
						 	   		break; 
					
		    	}
    		}
    	});
		
		
	}

    @FXML
    void qtmouse_exit(MouseEvent event) {
    	Platform.runLater(new Runnable() {
    		public void run()
    		{
    			btn_Quote.setStyle(null);
		        set_style(theme, sectheme);
		        img_quote.setImage(null);
		        img_quote.setImage(new Image("/images/bear-"+sectheme+".png"));
    		}
    	});
    	
    	
	}
	
    @FXML
    void quote2_open(ActionEvent event) {
    	open_quote();
    }
    
    void open_quote()
    {
    	FXML_Switcher ui = new FXML_Switcher();
		Pane temp = ui.getScene("/fxml/StockQuote");
		
		borderpane.setCenter(temp);
    }
    
    @FXML
    void Quote_Open(ActionEvent event) {
    	btn_Quote.setSelected(true);
    	open_quote();
    
    }

    @FXML
    void settmouse_enter(MouseEvent event) {
    	Platform.runLater(new Runnable() {
    		public void run()
    		{
    			switch(theme)
		    	{
					case "dark" :  btn_Settings.setStyle("-fx-text-fill:#121212;-fx-background-insets: 1px 1px 1px 1px rgba(0, 0, 0, 0.24);");
									img_settings.setImage(null);
									img_settings.setImage(new Image("/images/settings-dark.png"));
					     	   		break;
						
					case "white" :	btn_Settings.setStyle("-fx-text-fill:white;-fx-background-insets: 1px 1px 1px 1px rgba(0, 0, 0, 0.24);");
									img_settings.setImage(null);
									img_settings.setImage(new Image("/images/settings-white.png"));
						 	   		break; 
				}
    		}
    	});
    	
    }

    @FXML
    void quote2_enter(MouseEvent event) {
    	img_quote2.setImage(null);
    	img_quote2.setImage(new Image("/images/bear-"+sectheme+".png"));
    }

    @FXML
    void quote2_exit(MouseEvent event) {
    	img_quote2.setImage(null);
    	if(theme.equals("white"))
    	{
    		img_quote2.setImage(new Image("/images/bear-dark.png"));
    	}
    	else
    	{
    		img_quote2.setImage(new Image("/images/bear-white.png"));
    	}
    }

    @FXML
    void ticker_enter(MouseEvent event) {
    	img_ticker.setImage(null);
    	img_ticker.setImage(new Image("/images/Ticker-"+sectheme+".png"));
    }

    @FXML
    void ticker_exit(MouseEvent event) {
    	img_ticker.setImage(null);
    	img_ticker.setImage(new Image("/images/Ticker-"+theme+".png"));
    }    
    
    @FXML
    void ticker_open(ActionEvent event) {
    	open_settings();
    }

    @FXML
    void sett_open(ActionEvent event) {
    	open_settings();
    }
    
    @FXML
    void graph2_enter(MouseEvent event) {
    	img_graph2.setImage(null);
    	img_graph2.setImage(new Image("/images/bull-"+sectheme+".png"));
    }

    @FXML
    void graph2_exit(MouseEvent event) {
    	img_graph2.setImage(null);
    	if(theme.equals("white"))
    	{
    		img_graph2.setImage(new Image("/images/bull-dark.png"));
    	}
    	else
    	{
    		img_graph2.setImage(new Image("/images/bull-white.png"));
    	}
    }
    
    
    @FXML
    void settmouse_exit(MouseEvent event) {

    	Platform.runLater(new Runnable() {
    		public void run()
    		{
    			btn_Settings.setStyle(null);
			    set_style(theme, sectheme);
			    img_settings.setImage(null);
			    img_settings.setImage(new Image("/images/settings-"+sectheme+".png"));
    		}
    	});
    }

    void open_settings()
    {
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Settings.fxml"));
			Pane newPane = loader.load();
			Settings_Controller themesec = loader.getController();
			themesec.setParent(this);
	    	borderpane.setCenter(newPane);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    
    @FXML
    void Settings_Open(ActionEvent event) {
    	btn_Settings.setStyle("-fx-background-insets: 0 0 -1 0, 0, 1, 1;");
    	open_settings();
    	
    	}

	}