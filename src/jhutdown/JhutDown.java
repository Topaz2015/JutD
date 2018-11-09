/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhutdown;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Bernard Mutahi
 */
public class JhutDown extends Application {
//    private final int DAYS = 30, HOURS = 48, MINS = 60, SECS = 60;
    
    @FXML
    ChoiceBox fdays, fmins, fhrs;
    @FXML
    ComboBox fsecs;    
    @FXML
    Label txOut;    
    @FXML
    Button btShut, btReboot, btCancel; 
    @FXML
    protected void handleShut(ActionEvent event) {
        long time = fetchTime();
        String str = "System will shut down in " + time + " seconds";
        txOut.setText(str);
        try {
            File file = new File("d:\\shutd782.bat");
            if(!file.exists()) file.createNewFile();
            file.setWritable(true);
            String st = "@echo off\n shutdown.exe -s -t " + time + "\n exit";
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(st);
            writer.flush();
            
            String[] command = {"cmd.exe", "/C", "Start", "d:\\shutd782.bat"};
            Process p = Runtime.getRuntime().exec(command);
//            file.
//            Runtime.getRuntime().exec(str);
        } 
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    @FXML
    protected void handleRebt(ActionEvent event) {
        
    }
    @FXML
    protected void handleCanc(ActionEvent event) {
        try {
        File file = new File("d:\\shutd782.bat");
            if(!file.exists()) file.createNewFile();
            file.setWritable(true);
            String st = "@echo off\n shutdown.exe /a\n exit";
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(st);
            writer.flush();
            
            String[] command = {"cmd.exe", "/C", "Start", "d:\\shutd782.bat"};
            Process p = Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            txOut.setText(ex.toString());
            System.out.println(ex.toString());
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
             
        FXMLLoader loader = new FXMLLoader(getClass().getResource("JhutGui.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        populateSpinner();
        
        Scene scene = new Scene(root);
//        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("J-SHUTDOWWN TESTER");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
//        primaryStage.resizableProperty().setValue(false);
               
    }
    private long fetchTime(){
        long secs = Long.valueOf((String) fsecs.valueProperty().getValue());
        long mins = Long.valueOf((String) fmins.valueProperty().getValue());
        long hrs = Long.valueOf((String) fhrs.valueProperty().getValue());
        long days = Long.valueOf((String) fdays.valueProperty().getValue());
        secs = secs + mins*60 + hrs*60*60 + days*24*60*60;
        return secs;
    }
    private void populateSpinner(){
        fdays.getItems().addAll(NumsEnum.getList(NumsEnum.DAYS));
        fhrs.getItems().addAll(NumsEnum.getList(NumsEnum.HOURS));
        fmins.getItems().addAll(NumsEnum.getList(NumsEnum.MINS));
        fsecs.getItems().addAll(NumsEnum.getList(NumsEnum.SECS));
        
        fdays.valueProperty().setValue(NumsEnum.getDefault(NumsEnum.DAYS));
        fhrs.valueProperty().setValue(NumsEnum.getDefault(NumsEnum.HOURS));
        fmins.valueProperty().setValue(NumsEnum.getDefault(NumsEnum.MINS));
        fsecs.valueProperty().setValue(NumsEnum.getDefault(NumsEnum.SECS));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
