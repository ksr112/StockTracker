package com.kaushiksitaraman.StockTracker;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	FileData dataconn = new FileData();
	
	public void start(Stage stage) throws Exception {
		
		Parent root =FXMLLoader.load(getClass().getResource("/fxml/First.fxml"));
		stage.setTitle("Stock");
		 stage.setScene(new Scene(root, 627, 483 ));
		 stage.resizableProperty().set(false);
		 stage.show();
		 stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		      public void handle(WindowEvent we) {
		    	  
		    	  dataconn.set_data();	    	  
		    	  Platform.exit();
		          System.exit(0);
		      }
		  }); 

		 
	}
	
	
	
	public static void main(String[] args) {
		
		launch(args);

	}
}
