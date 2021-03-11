package com.kaushiksitaraman.StockTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.stage.Stage;

public class FileData {
  private static String theme;
  
  private static String secTheme;
  
  private static String key;
  
  private static String bsealive_cntr;
  
  private static String nsealive_cntr;
  
  private static String font;
  
  private static String speed;
  
  private ArrayList<String> file_data = new ArrayList<>();
  
  private static TickerBSE_Controller bse_ticker;
  
  private static TickerNSE_Controller nse_ticker;
  
  private static Stage bse_stage;
  
  private static Stage nse_stage;
  
  private static double stageheight_BSE;
  
  private static double stageheight_NSE;
  
 public double getStageheight_BSE() {
    return stageheight_BSE;
  }
  
  public void setStageheight_BSE(double stageheight_BSE) {
    FileData.stageheight_BSE = stageheight_BSE;
  }
  
  public double getStageheight_NSE() {
    return stageheight_NSE;
  }
  
  public void setStageheight_NSE(double stageheight_NSE) {
    FileData.stageheight_NSE = stageheight_NSE;
  }
  
  public Stage getBse_Stage() {
    return bse_stage;
  }
  
  public void setBse_Stage(Stage bse_stage) {
    FileData.bse_stage = bse_stage;
   }
  
  public Stage getNse_Stage() {
    return nse_stage;
  }
  
  public void setNse_Stage(Stage nse_stage) {
    FileData.nse_stage = nse_stage;
    }
  
  public TickerNSE_Controller getNse_ticker() {
    return nse_ticker;
  }
  
  public void setNse_ticker(TickerNSE_Controller nse_ticker) {
    FileData.nse_ticker = nse_ticker;
  }
  
  public TickerBSE_Controller getBse_ticker() {
    return bse_ticker;
  }
  
  public void setBse_ticker(TickerBSE_Controller bse_ticker) {
    FileData.bse_ticker = bse_ticker;
  }
  
  public String getTheme() {
    return theme;
  }
  
  public void setTheme(String theme) {
    FileData.theme = theme;
  }
  
  public String getSecTheme() {
    return secTheme;
  }
  
  public void setSecTheme(String secTheme) {
    FileData.secTheme = secTheme;
  }
  
  public String getKey() {
    return key;
  }
  
  public void setKey(String key) {
    FileData.key = key;
  }
  
  public String getBseAlive_cntr() {
    return bsealive_cntr;
  }
  
  public void setBseAlive_cntr(String alive_cntr) {
    bsealive_cntr = alive_cntr;
  }
  
  public String getNseAlive_cntr() {
    return nsealive_cntr;
  }
  
  public void setNseAlive_cntr(String nsealive_cntr) {
    FileData.nsealive_cntr = nsealive_cntr;
  }
  
  public String getFont() {
    return font;
  }
  
  public void setFont(String font) {
    FileData.font = font;
  }
  
  public String getSpeed() {
    return speed;
  }
  
  public void setSpeed(String speed) {
    FileData.speed = speed;
  }
  
  public void get_data() {
    File file = new File(".Settings.ser");
    try {
      FileInputStream Read = new FileInputStream(file);
      ObjectInputStream in = new ObjectInputStream(Read);
      this.file_data = (ArrayList<String>)in.readObject();
      in.close();
      Read.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } 
    theme = this.file_data.get(0);
    secTheme = this.file_data.get(1);
    key = this.file_data.get(2);
    font = this.file_data.get(3);
    speed = this.file_data.get(4);
  }
  
  public void set_data() {
    this.file_data.add(theme.trim());
    this.file_data.add(secTheme.trim());
    this.file_data.add(key.trim());
    this.file_data.add(font.trim());
    this.file_data.add(speed.trim());
    File file = new File(".Settings.ser");
    try {
      FileOutputStream Write = new FileOutputStream(file);
      ObjectOutputStream out = new ObjectOutputStream(Write);
      out.writeObject(this.file_data);
      out.close();
      Write.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
