package org.imslab;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    		Controller controller = Controller.getInstance();
    		controller.initialize(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
