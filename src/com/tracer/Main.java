package com.tracer;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
//	        Parent root = FXMLLoader.load(getClass().getResource("fxml_home.fxml"));
			initLayout(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void initLayout(Stage primaryStage) {
        Parent root;
		try {
			root = FXMLLoader.load(Main.class.getResource(("view//fxml_home.fxml")));
			primaryStage.setTitle("FXML Welcome");
			primaryStage.setScene(new Scene(root, 300, 275));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
