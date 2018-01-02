package org.imslab;

import org.imslab.scene.SceneManager;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
	    	SceneManager.setPrimaryStage(primaryStage);
	    	primaryStage.getIcons().add(new Image("file:src/org.imslab.scene.ui.background.png"));
	    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
