package com.kaushiksitaraman.StockTracker;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddRemove_Controller {

    @FXML
    private RadioButton rdbtn_BSE;

    @FXML
    private RadioButton rdbtn_NSE;

    @FXML
    private TableView<Stock> tblvw_Stock;

    @FXML
    private TableColumn<Stock , String> tblcl_Symbol;

    @FXML
    private TableColumn<Stock , String> tblcl_Name;

    @FXML
    private TextField txt_Symbol;

    @FXML
    private Pane pane;
    
    @FXML
    private HBox hbox1;
    
    @FXML
    private HBox hbox2;
    
    @FXML
    private HBox hbox3;
    
    @FXML
    private Button btn_Add;

    @FXML
    private Button btn_Delete;

    @FXML
    private Button btn_Reset;

    @FXML
    private Button btn_Close;
    
    @FXML
    private Label lbl1;
    
    @FXML
    private Hyperlink link1;
    
    FileData dataconn = new FileData();
    
    EntriesDB entrydb = new EntriesDB();
    
    private boolean isPresent;
    
    private static final Logger log = LoggerFactory.getLogger(FirstController.class);
    
    public void initialize()
    {	
    	log.debug("Disabling the buttons and fields");
    	btn_Add.setDisable(true);
		btn_Reset.setDisable(true);
//		btn_Delete.setDisable(true);
		txt_Symbol.setDisable(true);
		tblcl_Symbol.setVisible(false);
    	tblcl_Name.setVisible(false);
    	tblvw_Stock.setVisible(false);
    	log.debug("Applying theme");
    	if(dataconn.getTheme().equals("dark"))
    	{
    		pane.getStyleClass().add("dark");
    		lbl1.getStyleClass().add("lbl-dark");
    	}
    	else
    	{
    		pane.getStyleClass().add("white");
    		lbl1.getStyleClass().add("lbl-white");
    	}
    	
    	
    	switch(dataconn.getSecTheme())
    	{
	    	case "Lime" : set_Style(dataconn.getTheme() , "Lime" );
	    				break;
	    	case "Purple" : set_Style(dataconn.getTheme() , "Purple" );
	    				 break;
	    	case "Turquoise" : set_Style(dataconn.getTheme() , "Turquoise" );
	    					break;
	    	case "Blue" : set_Style(dataconn.getTheme() , "Blue" );
	    			   break;
	    	case "Orange" : set_Style(dataconn.getTheme() , "Orange" );
	    				 break;
    				 
    	}  	
    	log.debug("Binding buttons");
    	btn_Delete.disableProperty().bind(Bindings.isEmpty(tblvw_Stock.getSelectionModel().getSelectedItems()));
    	btn_Add.disableProperty().bind(txt_Symbol.textProperty().isEmpty());
    	lbl1.visibleProperty().bind(rdbtn_BSE.selectedProperty().or(rdbtn_NSE.selectedProperty()));
    	link1.visibleProperty().bind(rdbtn_BSE.selectedProperty().or(rdbtn_NSE.selectedProperty()));
    }
    
    private void set_Style(String theme , String secTheme)
    {
    	
    	btn_Add.getStyleClass().add("btn-"+theme+"-"+secTheme);
    	btn_Delete.getStyleClass().add("btn-"+theme+"-"+secTheme);
    	btn_Reset.getStyleClass().add("btn-"+theme+"-"+secTheme);
    	btn_Close.getStyleClass().add("btn-"+theme+"-"+secTheme);
    	rdbtn_BSE.getStyleClass().add("rdbtn-"+theme+"-"+secTheme);
    	rdbtn_NSE.getStyleClass().add("rdbtn-"+theme+"-"+secTheme);
    	link1.getStyleClass().add("link-"+secTheme);
    	tblvw_Stock.getStyleClass().add("table-view-"+theme+"-"+secTheme);
    	
    	
    }
    
    @FXML
    void add_Symbol(ActionEvent event) {
    	
    	if(rdbtn_BSE.isSelected())
    	{
    		
    		String text = txt_Symbol.getText().toUpperCase();
    		txt_Symbol.setText("");
    		int strt = text.length()-3;
    		int end = text.length();
    		if(text.substring(strt, end).equals(".BO") || text.equals(""))
    		{
    				Thread t = new Thread( new Runnable() {
    					 public void run()
    					 {
    						 ConnStockQuote connection = new ConnStockQuote();
    						 log.debug("Checking whether the data already exists");
    						for(int i = 0 ; i < dataconn.getBse_Symbols().size(); i++)
    						{
    							if(dataconn.getBse_Symbols().get(i).equals(text))
    							{
    								isPresent = true;
    							}
    							
    						}
    						 try {
    							 if(isPresent)
    							 {
    								 Platform.runLater(new Runnable() {
    									public void run()
    									{
    										Alert entry_exists = new Alert(AlertType.ERROR);
											entry_exists.setTitle("Error");
											entry_exists.setHeaderText(null);
											entry_exists.setContentText("Value already exists");
											ButtonType Ok = new ButtonType("Okay");
											DialogPane dialogPane = entry_exists.getDialogPane();
											entry_exists.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
											entry_exists.getButtonTypes().setAll( Ok );
											Stage stage = (Stage) entry_exists.getDialogPane().getScene().getWindow();
											 stage.setAlwaysOnTop(true);
											Node Okay = entry_exists.getDialogPane().lookupButton(Ok);
											Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
											dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
											entry_exists.show();
    									}
    								 });
    							 }
    							 else
    							 {
									String result = connection.get_connection(dataconn.getKey(), text);
									if(result.equals("IOException"))
									{	
										Platform.runLater(new Runnable() {
											public void run()
											{
												
												Alert entry_error = new Alert(AlertType.ERROR);
												entry_error.setTitle("Error");
												entry_error.setHeaderText(null);
												entry_error.setContentText("Value entered does not exist, please check again");
												ButtonType Ok = new ButtonType("Okay");
												DialogPane dialogPane = entry_error.getDialogPane();
												entry_error.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
												entry_error.getButtonTypes().setAll( Ok );
												Stage stage = (Stage) entry_error.getDialogPane().getScene().getWindow();
												 stage.setAlwaysOnTop(true);
												Node Okay = entry_error.getDialogPane().lookupButton(Ok);
												Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
												dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
												entry_error.show();
												
											}
										});
										
									}
									else
									{
										System.out.println(result);
										String name = connection.get_longname(result);
										try {
											Connection conn = entrydb.getBSEConnection();
											entrydb.add_db_row(conn, "BSE", text, name);
											log.debug("Done");
											tblcl_Symbol.setCellValueFactory( new PropertyValueFactory<Stock , String>("Symbol"));
											tblcl_Name.setCellValueFactory( new PropertyValueFactory<Stock , String>("Name"));
						    	    		ObservableList<Stock> bse_data;
											bse_data = EntriesDB.getBSEdata(conn);
											tblvw_Stock.setItems(bse_data);
											}
											catch (ClassNotFoundException | SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
									}
								}
								
							} catch (IOException e) {
								e.printStackTrace();
							}
    					 }
    				});t.start();
    		}
    		else
    		{
    			
    			Platform.runLater(new Runnable() {
					public void run()
					{
						
						System.out.println("error");
		    			Alert entry_error = new Alert(AlertType.ERROR);
						entry_error.setTitle("Error");
						entry_error.setHeaderText(null);
						entry_error.setContentText("This does not have .BO or .bo, please retry again");
						ButtonType Ok = new ButtonType("Okay");
						DialogPane dialogPane = entry_error.getDialogPane();
						Stage stage = (Stage) entry_error.getDialogPane().getScene().getWindow();
						 stage.setAlwaysOnTop(true);
						entry_error.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
						entry_error.getButtonTypes().setAll( Ok );
						Node Okay = entry_error.getDialogPane().lookupButton(Ok);
						Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
						dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
						entry_error.show();
						
					}
				});
    			
    		}
    		
    	}
    	else
    	{
    		String text = txt_Symbol.getText().toUpperCase();
    		int strt = text.length()-3;
    		int end = text.length();
    		if(text.substring(strt, end).equals(".NS") || text.equals("") )
    		{
    			Thread t = new Thread( new Runnable() {
					 public void run()
					 {
						 ConnStockQuote connection = new ConnStockQuote();
						 log.debug("Checking whether the data already exists");
 						for(int i = 0 ; i < dataconn.getNse_Symbols().size(); i++)
 						{
 							if(dataconn.getNse_Symbols().get(i).equals(text))
 							{
 								isPresent = true;
 							}
 							
 						}
 						 try {
 							 if(isPresent)
 							 {
 								 Platform.runLater(new Runnable() {
 									public void run()
 									{
 										Alert entry_exists = new Alert(AlertType.ERROR);
											entry_exists.setTitle("Error");
											entry_exists.setHeaderText(null);
											entry_exists.setContentText("Value already exists");
											ButtonType Ok = new ButtonType("Okay");
											DialogPane dialogPane = entry_exists.getDialogPane();
											entry_exists.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
											entry_exists.getButtonTypes().setAll( Ok );
											Stage stage = (Stage) entry_exists.getDialogPane().getScene().getWindow();
											 stage.setAlwaysOnTop(true);
											Node Okay = entry_exists.getDialogPane().lookupButton(Ok);
											Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
											dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
											entry_exists.show();
 									}
 								 });
 							 }
 							 else
 							 {
								String result = connection.get_connection(dataconn.getKey(), text);
								if(result.equals("IOException"))
								{
									
									Platform.runLater(new Runnable() {
										public void run()
										{
											
											Alert entry_error = new Alert(AlertType.ERROR);
											entry_error.setTitle("Error");
											entry_error.setHeaderText(null);
											entry_error.setContentText("Value entered does not exist, please check again");
											ButtonType Ok = new ButtonType("Okay");
											DialogPane dialogPane = entry_error.getDialogPane();
											entry_error.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
											entry_error.getButtonTypes().setAll( Ok );
											Stage stage = (Stage) entry_error.getDialogPane().getScene().getWindow();
											 stage.setAlwaysOnTop(true);
											Node Okay = entry_error.getDialogPane().lookupButton(Ok);
											Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
											dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
											entry_error.show();
											
										}
									});
									
								}
								else if(result.contains("You have exceeded the DAILY quota"))
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
												Stage stage = (Stage) over_quote.getDialogPane().getScene().getWindow();
												 stage.setAlwaysOnTop(true);
												Node Okay = over_quote.getDialogPane().lookupButton(Ok);
												Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
												dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
												over_quote.show();
												
	
											}
	
										});				
								}
								else
								{
									String name = connection.get_longname(result);
									try {
										Connection conn = entrydb.getNSEConnection();
										entrydb.add_db_row(conn, "NSE", text, name);
										log.debug("Done");
										tblcl_Symbol.setCellValueFactory( new PropertyValueFactory<Stock , String>("Symbol"));
										tblcl_Name.setCellValueFactory( new PropertyValueFactory<Stock , String>("Name"));
					    	    		ObservableList<Stock> nse_data;
										nse_data = EntriesDB.getNSEdata(conn);
										tblvw_Stock.setItems(nse_data);
									} catch (ClassNotFoundException | SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								  }
 							   }
							}catch (IOException e) {
								e.printStackTrace();
							}
						 }
				});t.start();
    		}
    		else
    		{
    			
    			Platform.runLater(new Runnable() {
					public void run()
					{
						
						Alert entry_error = new Alert(AlertType.ERROR);
						entry_error.setTitle("Error");
						entry_error.setHeaderText(null);
						entry_error.setContentText("This does not have .NS or .ns , please retry again");
						ButtonType Ok = new ButtonType("Okay");
						DialogPane dialogPane = entry_error.getDialogPane();
						entry_error.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
						entry_error.getButtonTypes().setAll( Ok );
						Node Okay = entry_error.getDialogPane().lookupButton(Ok);
						Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
						dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
						entry_error.show();
						
					}
				});
    			
    		}
    		
    	}
    	
    }

    @FXML
    void close_Window(ActionEvent event) {

    	Stage stage = FileData.getAddrem();
    	stage.close();
    	
    }
    
    @FXML
    void open_link(ActionEvent event) {
    	
    	log.debug("Starting the system default browser to open the link");
        if(Desktop.isDesktopSupported())
        {	new Thread(() -> {
            try {
                Desktop.getDesktop().browse(new URI("https://in.finance.yahoo.com/"));
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
    void delete_Entry(ActionEvent event) {

    	Platform.runLater(new Runnable() {

			@Override
			public void run() {
				
				String symbol = tblvw_Stock.getSelectionModel().getSelectedItem().getSymbol().toString();
		    	String table = null;
		    	Connection conn = null;
		    	Alert yousure = new Alert(AlertType.CONFIRMATION);
				yousure.setTitle("Delete");
				yousure.setHeaderText("This will delete the entry");
				yousure.setContentText("You want to continue ?");
				ButtonType Ok = new ButtonType("Okay");
				ButtonType Cancel = new ButtonType("Cancel");
				DialogPane dialogPane = yousure.getDialogPane();
				yousure.getDialogPane().getStylesheets().add(getClass().getResource("/styles/myDialogs.css").toString());
				yousure.getButtonTypes().setAll(Ok , Cancel);
				Stage stage = (Stage) yousure.getDialogPane().getScene().getWindow();
				 stage.setAlwaysOnTop(true);
				Node Okay = yousure.getDialogPane().lookupButton(Ok);
				Okay.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
				Node Cancelnd = yousure.getDialogPane().lookupButton(Cancel);
				Cancelnd.setId("btn-"+dataconn.getTheme()+"-"+dataconn.getSecTheme());
				dialogPane.getStyleClass().add("myDialog-"+dataconn.getTheme());
				Optional<ButtonType> result = yousure.showAndWait();
				if (result.get() == Ok){
				    
					if(rdbtn_BSE.isSelected())
				    {
				    		try {
				    			table = "BSE";
				        		conn = entrydb.getBSEConnection();
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
				    	}
				    	else
				    	{
				    		try {
				    			table = "NSE";
				        		conn = entrydb.getNSEConnection();
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
				    	}
				    	
				    	try {
							entrydb.delete_db_entry(conn, table, symbol);
						} catch (SQLException e) {
							e.printStackTrace();
						}
				    	int index = tblvw_Stock.getSelectionModel().getSelectedIndex();
				    	tblvw_Stock.getItems().remove(index);
				    	if(table == "BSE")
				    	{
				    		try {
				    			dataconn.remove_Bse_entries();
				        		dataconn.setBse_Names(entrydb.getName(conn, "BSE"));
								dataconn.setBse_Symbols(entrydb.getSymbol(conn, "BSE"));
								tblvw_Stock.getSelectionModel().select(null);
								tblvw_Stock.focusModelProperty().get().focus(null);
				    		} catch (SQLException e) {
								e.printStackTrace();
							}
							
				    	}
				    	else
				    	{
				    		try {
				    			dataconn.remove_Nse_entries();
				        		dataconn.setNse_Names(entrydb.getName(conn, "NSE"));
								dataconn.setNse_Symbols(entrydb.getSymbol(conn, "NSE"));
								tblvw_Stock.getSelectionModel().select(null);
								tblvw_Stock.focusModelProperty().get().focus(null);
				    		} catch (SQLException e) {
								e.printStackTrace();
							}
							}
				    	log.debug("Done");
					
				} else {
				    yousure.close();
				}
			
			}
			
    	});

    }

    @FXML
    void reset(ActionEvent event) {
    	log.debug("getting everything to default");
    	rdbtn_NSE.relocate(253, 17);
		rdbtn_BSE.setVisible(true);
		rdbtn_NSE.setSelected(false);
		rdbtn_BSE.setSelected(false);
		tblvw_Stock.setItems(null);
		tblvw_Stock.setVisible(false);
		tblcl_Symbol.setVisible(false);
		tblcl_Name.setVisible(false);
		btn_Reset.setDisable(true);
		txt_Symbol.setDisable(true);
		rdbtn_BSE.relocate(141, 17);
		rdbtn_NSE.setVisible(true);
		tblvw_Stock.setItems(null);
		
    }

    @FXML
    void show_BSE(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	if(rdbtn_BSE.isSelected())
    	{
    		rdbtn_BSE.relocate(197, 17);
    		rdbtn_NSE.setVisible(false);
    		Thread t = new Thread()
			{
    			
    			public void run()
    			{
    				try {
						Connection conn = entrydb.getBSEConnection();
						tblcl_Symbol.setCellValueFactory( new PropertyValueFactory<Stock , String>("Symbol"));
						tblcl_Name.setCellValueFactory( new PropertyValueFactory<Stock , String>("Name"));
	    	    		ObservableList<Stock> bse_data;
						bse_data = EntriesDB.getBSEdata(conn);
						Platform.runLater(new Runnable() {
							public void run()
							{	
								log.debug("Making buttons and fields visible");
								tblvw_Stock.setVisible(true);;
					    		tblcl_Symbol.setVisible(true);
					    		tblcl_Name.setVisible(true);
					    		btn_Reset.setDisable(false);
					    		txt_Symbol.setDisable(false);
							}
						});
						log.debug("Populating the Table");
						tblvw_Stock.setItems(bse_data);
					} catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	    		
    			}
    			
			};t.start();
    		
    	}
    	else
    	{
    		rdbtn_BSE.relocate(141, 17);
    		rdbtn_NSE.setVisible(true);
    		tblvw_Stock.setItems(null);
    		tblvw_Stock.setVisible(false);
    		tblcl_Symbol.setVisible(false);
    		tblcl_Name.setVisible(false);
    		btn_Reset.setDisable(true);
    		txt_Symbol.setDisable(true);
    		
    	}
    	
    }

    @FXML
    void show_NSE(ActionEvent event) {
    	
    	if(rdbtn_NSE.isSelected())
    	{
    		rdbtn_NSE.relocate(197, 17);
    		rdbtn_BSE.setVisible(false);
    		Thread t = new Thread()
			{
    			
    			public void run()
    			{
    				try {
						Connection conn = entrydb.getNSEConnection();
						tblcl_Symbol.setCellValueFactory( new PropertyValueFactory<Stock , String>("Symbol"));
						tblcl_Name.setCellValueFactory( new PropertyValueFactory<Stock , String>("Name"));
	    	    		ObservableList<Stock> nse_data;
						nse_data = EntriesDB.getNSEdata(conn);
						Platform.runLater(new Runnable() {
							public void run()
							{
								log.debug("Making buttons and fields visible");
								tblvw_Stock.setVisible(true);;
					    		tblcl_Symbol.setVisible(true);
					    		tblcl_Name.setVisible(true);
					    		btn_Reset.setDisable(false);
					    		txt_Symbol.setDisable(false);
					    	}
						});
						log.debug("Populating the Table");
						tblvw_Stock.setItems(nse_data);
					} catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	    		
    			}
    			
			};t.start();
				
    	}
    	else
    	{
    		rdbtn_NSE.relocate(253, 17);
    		rdbtn_BSE.setVisible(true);
    		tblvw_Stock.setItems(null);
    		tblvw_Stock.setVisible(false);
    		tblcl_Symbol.setVisible(false);
    		tblcl_Name.setVisible(false);
    		btn_Reset.setDisable(true);
    		txt_Symbol.setDisable(true);
    	}
    	
    }

}
