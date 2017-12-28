package org.imslab;

import org.imslab.scene.SceneManager;
import org.imslab.state.StateManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	SceneManager.setPrimaryStage(primaryStage);
    	StateManager.getInstance();
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
