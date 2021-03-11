package com.kaushiksitaraman.StockTracker;

import java.text.DecimalFormat;

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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
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



public class TickerBSE_Controller {

    @FXML
    public AnchorPane anchor_pane;

    @FXML
    private VBox box_ticker;
    
    @FXML
    private GridPane pane;
    
    private String[] arrBSEcodes = {"^BSESN","ABAN.BO","ACC.BO",
    		"ADANIPORTS.BO",
    		"AMBUJACEM.BO",
    		"APOLLOHOSP.BO",
    		"ARVSMART.BO",
    		"ASHOKLEY.BO",
    		"ASIANPAINT.BO",
    		"AUROPHARMA.BO",
    		"AXISBANK.BO",
    		"BAJAJ-AUTO.BO",
    		"BAJAJFINSV.BO",
    		"BAJAJHLDNG.BO",
    		"BAJFINANCE.BO",
    		"BANKBARODA.BO",
    		"BERGEPAINT.BO",
    		"BHARATFORG.BO",
    		"BHARTIARTL.BO",
    		"BHEL.BO",
    		"BIOCON.BO",
    		"BOSCHLTD.BO",
    		"BPCL.BO",
    		"BRITANNIA.BO",
    		"CASTROLIND.BO",
    		"CIPLA.BO",
    		"COALINDIA.BO",
    		"COLPAL.BO",
    		"CONCOR.BO",
    		"CRANESSOFT.BO",
    		"CROMPTON.BO",
    		"DABUR.BO",
    		"DIVISLAB.BO",
    		"DIXON.BO",
    		"DLF.BO",
    		"DMART.BO",
    		"DRREDDY.BO",
    		"EICHERMOT.BO",
    		"EXIDEIND.BO",
    		"FEDERALBNK.BO",
    		"GAIL.BO",
    		"GODREJCP.BO",
    		"GRASIM.BO",
    		"HAPPSTMNDS.BO",
    		"HAVELLS.BO",
    		"HCLTECH.BO",
    		"HDFC.BO",
    		"HDFCLIFE.BO",
    		"HEROMOTOCO.BO",
    		"HINDALCO.BO",
    		"HINDPETRO.BO",
    		"HINDUNILVR.BO",
    		"IBULHSGFIN.BO",
    		"ICICIBANK.BO",
    		"ICICIPRULI.BO",
    		"IDEA.BO",
    		"IGL.BO",
    		"INDIGO.BO",
    		"INDUSINDBK.BO",
    		"INFY.BO",
    		"IOC.BO",
    		"IRCTC.BO",
    		"ITC.BO",
    		"JPASSOCIAT.BO",
    		"JSWSTEEL.BO",
    		"JUBLFOOD.BO",
    		"KARURVYSYA.BO",
    		"KOTAKBANK.BO",
    		"KTKBANK.BO",
    		"LAXMIMACH.BO",
    		"LICHSGFIN.BO",
    		"L&TFH.BO",
    		"LT.BO",
    		"LTI.BO",
    		"LUPIN.BO",
    		"M%26MFIN.BO",
    		"M%26M.BO",
    		"MARICO.BO",
    		"MARUTI.BO",
    		"MGL.BO",
    		"MOTHERSUMI.BO",
    		"MRF.BO",
    		"NAUKRI.BO",
    		"NESTLEIND.BO",
    		"NMDC.BO",
    		"NTPC.BO",
    		"ONGC.BO",
    		"PAGEIND.BO",
    		"PEL.BO",
    		"PETRONET.BO",
    		"PFC.BO",
    		"PIDILITIND.BO",
    		"PNBHOUSING.BO",
    		"POWERGRID.BO",
    		"RBLBANK.BO",
    		"RCOM.BO",
    		"RECLTD.BO",
    		"RELCAPITAL.BO",
    		"RELHOME.BO",
    		"RELIANCE.BO",
    		"SBILIFE.BO",
    		"SBIN.BO",
    		"SHREECEM.BO",
    		"SIEMENS.BO",
    		"SOUTHBANK.BO",
    		"SRTRANSFIN.BO",
    		"SUNPHARMA.BO",
    		"SUZLON.BO",
    		"TCS.BO",
    		"TATACONSUM.BO",
    		"TATAMOTORS.BO",
    		"TATAMTRDVR.BO",
    		"TATAPOWER.BO",
    		"TATASTEEL.BO",
    		"TECHM.BO",
    		"TITAN.BO",
    		"TVSMOTOR.BO",
    		"ULTRACEMCO.BO",
    		"UNITDSPR.BO",
    		"UPL.BO",
    		"VEDL.BO",
    		"VOLTAS.BO",
    		"WIPRO.BO",
    		"YESBANK.BO",
    		"ZEEL.BO" };
	    
    ConnTickerBSE connection = new ConnTickerBSE();
    
    Settings_Controller settings_conn = new Settings_Controller();
       
    Task<String> task;
    
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
    
    private int loop_counter;
    
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
    
     Color c;
    
    Polygon triangle = new Polygon();
    
    IntegerProperty ticker_index = new SimpleIntegerProperty();
    
    Label t_label = new Label();
    
    String theme;
    
    Text text = new Text();
    
    Settings_Controller controller;
    
    private FileData dataconn =  new FileData();

	Settings_Controller settings_Controller;
	
	volatile Boolean run = true;
	
	public void setParent(Settings_Controller settings_Controller) {
		this.settings_Controller = settings_Controller;
		
	}
    
    public void initialize()
    {	
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
    		pane.setStyle("-fx-background-color:#121212;");
    	}
    	else
    	{
    		theme = "white";
    		pane.setStyle("-fx-background-color:white;");
    	}
    	//pane.prefWidth(anchor_pane.getWidth());
    	start_threaded_ticker();
    	
    	ticker_index.addListener( new ChangeListener<Number>() {
    	    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
    	    	if( ticker_index.get() == arrBSEcodes.length-1 )
    	        {
    	        	
    	        	repeat_counter = 1;
    	        	start_threaded_ticker();
    	        	   	        	
    	        }
    	    }
    	 });
    }
    
    public void close_scene()
    {
    	task.cancel();
		Stage s = (Stage) box_ticker.getScene().getWindow();
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
    	String temp_compname = arrBSEcodes[index];
    	Text t;
    	if(index == 0)
    	{
    		
    		t = new Text(temp_compname.substring(1));
    		
    	}
    	else
    	{	if(temp_compname.contains("M%26MFIN"))
    		{
    			t = new Text("M&MFIN");
    		}
    		else if(temp_compname.contains("M%26M"))
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
    	Stage ticker = (Stage) box_ticker.getScene().getWindow();
    	
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
						Thread.sleep(18000);
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
    	Stage ticker = (Stage) box_ticker.getScene().getWindow();
    			
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
					Thread.sleep(800);
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
    	response = connection.start_connection(arrBSEcodes[index], index );
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
						nointernet.show();
						close_scene();
						settings_Controller.uncheck_BSE_tick();
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
					
					Alert nointernet = new Alert(AlertType.ERROR);
					nointernet.setTitle("Error!");
					nointernet.setHeaderText(null);
					nointernet.setContentText("You have exceeded the DAILY quota for Requests on your current plan.");
					nointernet.show();
					close_scene();
					settings_Controller.uncheck_BSE_tick();
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
				if(index != 0){ Thread.sleep(1000);}
				connection.getdata(response, index);
				if(index != 0){ Thread.sleep(500);}
				flowtext = createText(index);
				if(index != 0){ Thread.sleep(500);}
			} catch (InterruptedException e) {
				Stage ticker = (Stage) box_ticker.getScene().getWindow();
				ticker.close();
				e.printStackTrace();
			}
		}
		
		settings_Controller.screen_height.setValue(Screen.getPrimary().getVisualBounds().getMaxY());
		return flowtext;
    }
    
    private void ticker_start( TextFlow flowtext )
    {
    	settings_Controller.screen_height.setValue(Screen.getPrimary().getVisualBounds().getMaxY());
		final TextFlow flowtext2 = new TextFlow(flowtext);
		Label t_label = new Label(null,flowtext2);
		t_label.setPrefHeight(10);
		t_label.setCache(true);
		t_label.setCacheHint(CacheHint.QUALITY);
		pane.getChildren().add(t_label);
		double screenwidth = pane.getWidth();
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
    	
			task = new Task<String>(){
			
						
						protected String call() throws Exception {
							while(run)
					    	{
								if(!run)
								{
									break;
								}
							for( int i = 0 ; i < arrBSEcodes.length ; i++)
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
								
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									Stage ticker = (Stage) box_ticker.getScene().getWindow();
									ticker.close();
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								flowtext = compute_data(i);
								Platform.runLater(new Runnable() {
			
									@Override
									public void run() {
										
										ticker_start( flowtext );
										  
									}
									
									
								});
							 }
					    	}
	  
							
							
							return null;
							
						}
			    		
			    	};
			    	    t = new Thread(task);
			    	    t.start();
		    	
			    	
			    	
	    }

    

}