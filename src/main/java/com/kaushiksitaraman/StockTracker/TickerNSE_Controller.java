package com.kaushiksitaraman.StockTracker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;



public class TickerNSE_Controller {

    @FXML
    public AnchorPane anchor_pane2;

    @FXML
    private VBox box_ticker2;
    
    @FXML
    private GridPane pane2;
    
    private String[] arrNSEcodes ;
	    
    ConnTickerNSE connection = new ConnTickerNSE();
    
    Settings_Controller settings_conn = new Settings_Controller();
       
    Task<String> task2;
    
    String price;
    
    String price_change;
    
    Text counter = new Text();
    
    TextFlow tempflowtext;
    
    String response;
    
    Duration startDuration;
    
    Duration endDuration;
    
    KeyValue   strtkeyval ;
    
    KeyFrame   strtkeyfrm;
    
    KeyValue   endkeyval;
    
    KeyFrame   endkeyfrm;
    
    static Timeline timeline = new Timeline();
    
    Thread t;
    
    Canvas canvas;
    
    Text seperator2;
    
    GraphicsContext gc;    
    
    int negative_counter = 0;
    
    TranslateTransition translate ;
    
    TextFlow flowtext = new TextFlow();
    
    String pattern = "##,##,##,###.##";
    
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	
	String temp_font;
	
	String temp_speed;
	
	private int font;
	
	private int speed;
	
	int repeat_counter = 0;
    
    int index = 0;
    
    int ddd = 2500;
    
    int exception_counter = 0;
    
    private int loop_counter;
    
     Color c;
     
    Polygon triangle = new Polygon();
    
    IntegerProperty ticker_index = new SimpleIntegerProperty();
    
    Label t_label = new Label();
    
    String theme;
    
    Text text = new Text();
    
    Settings_Controller controller;
    
    private FileData dataconn =  new FileData();

	Settings_Controller settings_Controller;
    
	int time_index = 0;
	
    public void initialize()
    {	
    	
    	ArrayList<String> temp = new ArrayList<String>();
    	temp = dataconn.getNse_Symbols();
    	arrNSEcodes = new String [temp.size()];
    	temp.remove(0);
    	Collections.sort(temp);
    	temp.add(0 , "^NSEI");
    	for( int i = 0 ; i < temp.size() ; i++)
    	{
    		
    		arrNSEcodes[i] = temp.get(i);
    		
    	}
    	
    	switch(dataconn.getFont())
           	 {
           	 
           	 	case "Small" :  font = 13;
           	 					break;
           	 	case "Medium" : font = 17;
           	 					break;
           	 	case "Large" : font  = 30;
           	 				   break;
      
           	 }
           	 
    	 switch(dataconn.getSpeed())
       	 {
				case "Slow" :  speed = 80;
								break;
				case "Normal" : speed = 50;
								break;
				case "Fast" : speed = 30;
				   			   break;
       	 }
           
           	 
//           	 font = settings_conn.getfont();
//           	 speed = settings_conn.getspeed();
    	
    	if( dataconn.getTheme().equals("dark") )
    	{	
    		theme = "dark";
    		pane2.setStyle("-fx-background-color:#121212;");
    	}
    	else
    	{
    		theme = "white";
    		pane2.setStyle("-fx-background-color:white;");
    	}
    	//pane.prefWidth(anchor_pane.getWidth());
    	start_threaded_ticker();
    	
    	ticker_index.addListener( new ChangeListener<Number>() {
    	    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
    	       if( ticker_index.get() == arrNSEcodes.length-1 )
    	        {
    	        	
    	        	repeat_counter = 1;
    	        	start_threaded_ticker();
    	        	   	        	
    	        }
    	    }
    	 });
    }
    
    public void close_scene()
    {
    	task2.cancel();
		Stage s = (Stage) box_ticker2.getScene().getWindow();
		s.close();
    }
    
    
    public Text getpricetext(int index)
    {
    	String temp_price = decimalFormat.format(Float.valueOf(connection.getprice(index)));
    	if(!temp_price.contains("."))
    	{
    		temp_price = temp_price +".00" ;;
    	}
    	Text t = new Text(temp_price);
		return t;
    	
    }
    
    public Text getpricechngtext(int index)
    {
    	String temp_pricechange = decimalFormat.format(Float.valueOf(connection.getprice_change(index)));
    	if(!temp_pricechange.contains("."))
    	{
    		temp_pricechange = temp_pricechange +".00" ;
    	}
    	
    	if(temp_pricechange.contains("-"))
		{	Text t = new Text(temp_pricechange.substring(1));
    		t.setFill(Color.RED);
    		negative_counter = 1;
    		return t;
		}
    	else
    	{
    		Text t = new Text(temp_pricechange);
    		t.setFill(Color.GREEN);
    		negative_counter = 0;
    		return t;
    	}
    	
    }
    public Text getcompnametext(int index)
    {
    	String temp_compname = arrNSEcodes[index];
    	Text t;
    	if(index == 0)
    	{
    		
    		t = new Text(temp_compname.substring(1));
    		
    	}
    	else
    	{
    		if(temp_compname.contains("M%26M"))
    		{
    			t = new Text("M&M");
    		}
    		else
    		{
    			t = new Text(temp_compname.substring(0, temp_compname.indexOf(".")));
    		}
    	}
    	if( theme.equals("dark") )
    	{
    		t.setStyle("-fx-fill:white");
    	}
    	else
    	{
    		t.setStyle("-fx-fill:#121212");
    	}
    	return t;
    }
    
    public void createpolygon(String value, Color c)
    {
    	if(value.equals("-"))
    	{	
    		
    		gc.setFill(Color.RED);
    		switch(font)
    		{
    			case 13 :  gc.fillPolygon(new double[] {0.0,5.0,10.0},new double[] {0.0,10.0,0.0},3);
						   gc.setStroke(c);
				           gc.strokePolygon(new double[] {0.0,5.0,10.0},new double[] {0.0,10.0,0.0},3);
				           break;
        		   
    			case 17 : gc.fillPolygon(new double[] {0.0,5.0,10.0},new double[] {10.0,20.0,10.0},3);
	        			  gc.setStroke(c);
		        	      gc.strokePolygon(new double[] {0.0,5.0,10.0},new double[] {10.0,20.0,10.0},3);
		        	      break;
		        	      
    			case 30 : gc.fillPolygon(new double[] {0.0,10.0,20.0},new double[] {10.0,30.0,10.0},3);
		        		  gc.setStroke(c);
		        		  gc.strokePolygon(new double[] {0.0,10.0,20.0},new double[] {10.0,30.0,10.0},3);
		        		  break;
    		}
    		
    	}
    	else
    	{
    		gc.setFill(Color.GREEN);
    		switch(font)
    		{
    			case 13 : gc.fillPolygon(new double[] {0.0,5.0,10.0},new double[] {10.0,0.0,10.0},3);
					      gc.setStroke(c);
			              gc.strokePolygon(new double[] {0.0,5.0,10.0},new double[] {10.0,0.0,10.0},3);
			              break;
    				
    			case 17 : gc.fillPolygon(new double[] {0.0,5.0,10.0},new double[] {20.0,10.0,20.0},3);
			  			  gc.setStroke(c);
			    	      gc.strokePolygon(new double[] {0.0,5.0,10.0},new double[] {20.0,10.0,20.0},3);
			    	      break;
    				
    			case 30 : gc.fillPolygon(new double[] {0.0,10.0,20.0},new double[] {30.0,10.0,30.0},3);
			      		  gc.setStroke(c);
			      		  gc.strokePolygon(new double[] {0.0,10.0,20.0},new double[] {30.0,10.0,30.0},3);
			      		  break;
    		}
    		
    		
    		
    	}
    }
    
    public TextFlow createText( int index)
    {	
    	switch(font)
    	{
    	
    	case 13 : canvas = new Canvas(10,10);
    			  break;
    	
    	case 17 : canvas = new Canvas(10,20);
    			  break;
    			  
    	case 30 : canvas = new Canvas(20,30);
    			  break;
    			  
    	}
    	gc = canvas.getGraphicsContext2D();
    	Text comp_name = getcompnametext(index);
    	comp_name.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, font-1));
    	Text price = getpricetext(index);
    	price.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, font));
    	Text price_change = new Text();
    	price_change = getpricechngtext(index);
    	price_change.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, font));
    	if(theme.equals("dark"))
		{
			c = Color.WHITE;
			
		}
		else
		{
			c = Color.BLACK;
		}
    	if(negative_counter == 1)
    	{	
    		String temp_value = "-";
    		price.setFill(Color.RED);
    		createpolygon(temp_value,c);
    	}
    	else
    	{
    		price.setFill(Color.GREEN);
    		String temp_value = "+";
    		createpolygon(temp_value,c);
    	}
		Text seperator1 = new Text("  ");
		seperator2 = new Text("     ");
		Text seperator3 = new Text("  ");
		Text seperator4 = new Text("  ");
		Text seperator5 = new Text("   ");
		Text seperator6 = new Text("   ");
		tempflowtext = new TextFlow(seperator1,comp_name,seperator2,price,seperator3,canvas,seperator4,price_change,seperator5,seperator6);
	
		return tempflowtext;
    }
    
    private void calculate_loopend_delay(int num)
    {
    	Stage ticker = (Stage) box_ticker2.getScene().getWindow();
    	
    	if(num == 1)
    	{
	    	switch(speed)
	    	{
	    	
	    	case 80 :	try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					ticker.close();
					e.printStackTrace();
				}break;
	    	
	    	case 50 :try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				ticker.close();
				e.printStackTrace();
			}break;
	    	
	    	case 30 :try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				ticker.close();
				e.printStackTrace();
			}break;
	    	
	    	}
    	}
	    else if(num == 2)
	    {
		    	switch(speed)
		    	{
		    	
		    	case 80 :	try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						ticker.close();
						e.printStackTrace();
					}break;
		    	
		    	case 50 :try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					ticker.close();
					e.printStackTrace();
				}break;
		    	
		    	case 30 :try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					ticker.close();
					e.printStackTrace();
				}break;
		    	
		    	}
    	}
	    else
	    {
			switch(speed)
			{
			
			case 80 :	try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					ticker.close();
					e.printStackTrace();
				}break;
			
			case 50 :try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				ticker.close();
				e.printStackTrace();
			}break;
			
			case 30 :try {
				Thread.sleep(12000);
			} catch (InterruptedException e) {
				ticker.close();
				e.printStackTrace();
			}break;
			
			}
	   }
    }
    
    private void calculate_delay(int num)
    {	
    	Stage ticker = (Stage) box_ticker2.getScene().getWindow();
    			
    	if(num == 1)
    	{
	    	switch(speed)
	    	{
	    	
	    	case 80 :	try {
					Thread.sleep(4500);
				} catch (InterruptedException e) {
					ticker.close();
					e.printStackTrace();
				}break;
	    	
	    	case 50 :try {
				Thread.sleep(2250);
			} catch (InterruptedException e) {
				ticker.close();
				e.printStackTrace();
			}break;
	    	
	    	case 30 :try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				ticker.close();
				e.printStackTrace();
			}break;
	    	
	    	}
    	}
	    else if(num == 2)
	    {
		    	switch(speed)
		    	{
		    	
		    	case 80 :	try {
						Thread.sleep(6500);
					} catch (InterruptedException e) {
						ticker.close();
						e.printStackTrace();
					}break;
		    	
		    	case 50 :try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					ticker.close();
					e.printStackTrace();
				}break;
		    	
		    	case 30 :try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					ticker.close();
					e.printStackTrace();
				}break;
		    	
		    	}
    	}
	    else
	    {
			switch(speed)
			{
			
			case 80 :	try {
					Thread.sleep(12000);
				} catch (InterruptedException e) {
					ticker.close();
				e.printStackTrace();
				}break;
			
			case 50 :try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				ticker.close();
				e.printStackTrace();
			}break;
			
			case 30 :try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				ticker.close();
				e.printStackTrace();
			}break;
			
			}
	   }
    }
    
    private TextFlow compute_data(int index)
    {
    	if(index != 0)
    	{
    		switch(font)
			{
				case 13 : calculate_delay(1);
						  break;
						  
				case 17 : calculate_delay(2);
				  		  break;
				  		  
				case 30 : calculate_delay(3);
				    	  break;
				    	  
			}
    	}
    	response = connection.start_connection(arrNSEcodes[index], index );
    	settings_Controller.screen_height.setValue(Screen.getPrimary().getVisualBounds().getMaxY());
		ticker_index.set(index);
		if(response.equals("IOException"))
		{
			
			++exception_counter;
			if(exception_counter == 3)
			{
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
						close_scene();
						settings_Controller.uncheck_NSE_tick();
						dataconn.setNseAlive_cntr("false");
						try {
							t.interrupt();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				
			}
													
		}
		else if( response.contains("You have exceeded the DAILY quota") )
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
					close_scene();
					settings_Controller.uncheck_NSE_tick();
					dataconn.setNseAlive_cntr("false");
					try {
						t.interrupt();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});				
		}
		else
		{
			try {
				if(index != 0){ Thread.sleep(500);}
				exception_counter =0;
				connection.getdata(response, index);
				if(index != 0){ Thread.sleep(500);}
				flowtext = createText(index);
				if(index != 0 ){ if(speed != 10) { Thread.sleep(800); } }
			} catch (InterruptedException e) {
				Stage ticker = (Stage) box_ticker2.getScene().getWindow();
				ticker.close();
				e.printStackTrace();
			}
		}
		
		settings_Controller.screen_height.setValue(Screen.getPrimary().getVisualBounds().getMaxY());
		return flowtext;
    }
    
    private void ticker_start(TextFlow flowtext)
    {
    	settings_Controller.screen_height.setValue(Screen.getPrimary().getVisualBounds().getMaxY());
		final TextFlow flowtext2 = new TextFlow(flowtext);
		Label t_label = new Label(null,flowtext2);
		t_label.setPrefHeight(10);
		t_label.setCache(true);
		t_label.setCacheHint(CacheHint.QUALITY);
		pane2.getChildren().add(t_label);
		double screenwidth = pane2.getWidth();
	  	  startDuration = Duration.ZERO;
	  	   endDuration = Duration.seconds(speed);
	  	   
	  	   strtkeyval = new KeyValue(t_label.translateXProperty(), screenwidth,Interpolator.LINEAR);
	  	   
	  	   strtkeyfrm = new KeyFrame(startDuration, strtkeyval);
	  	   
	  	   endkeyval = new KeyValue(t_label.translateXProperty(), -1.0 * screenwidth,Interpolator.LINEAR);
	  	   
	  	   endkeyfrm = new KeyFrame(endDuration, endkeyval);
	  	   timeline = new Timeline(strtkeyfrm, endkeyfrm);
	  	   if (loop_counter == 0) 
	  	   {
			timeline.play();
		}
    }
    
   private void start_threaded_ticker()
    {

    	
		task2 = new Task<String>(){
		
					
					protected String call() throws Exception {
						
						for( int i = 0 ; i < arrNSEcodes.length ; i++)
						{	
							if(repeat_counter ==1)
								{
									 
										switch(font)
										{
											case 13 : calculate_loopend_delay(1);
													  break;
													  
											case 17 : calculate_loopend_delay(2);
													  break;
											  		  
											case 30 : calculate_loopend_delay(3);
													  break;
											    	  
										}
										repeat_counter = 0 ;
										  
								}
							
								flowtext = compute_data(i); 
								Platform.runLater(new Runnable() {
		
								@Override
								public void run() {
										ticker_start(flowtext);

								}
								
								
							});
				}
  
						
						
						return null;
						
					}
		    		
		    	};
		    	    t = new Thread(task2);
		    	    t.start();
	    	
		    	
		    	
    }

	public void setParent(Settings_Controller settings_Controller) {
		this.settings_Controller = settings_Controller;
		
	}

}