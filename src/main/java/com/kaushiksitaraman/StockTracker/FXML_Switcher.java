package com.kaushiksitaraman.StockTracker;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

class FXML_Switcher {

	

		private Pane newPane;
		
		public Pane getScene(String filename)
		{
			try {
				URL fileurl = Main.class.getResource(filename + ".fxml");
				new FXMLLoader();
				newPane = FXMLLoader.load(fileurl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return(newPane);
		}


		
	}
	

