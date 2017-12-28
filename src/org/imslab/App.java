package org.imslab;

import org.imslab.scene.SceneManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    		// init all scene once the sceneManager is created.
    		SceneManager sceneManager = new SceneManager(primaryStage);
    		
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
