package org.imslab;

import org.imslab.scene.SceneManager;
import org.imslab.state.StateManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
	
	private StateManager stateManager;
	private SceneManager sceneManager;
	
	@FXML
	public Text output;	
	

    // XXX: Model acts as a DB? Replace it with DB?
    private Model model = new Model();
    
    private static Controller controller = null;

    private Controller() {
    		System.out.println("Create Controller");
    		stateManager = StateManager.getInstance();
    }
    
    // This will be invoked by SceneManager indirectly.
    public static Controller getInstance() {
    		if (controller == null) {
			controller = new Controller();
		}
    		return controller;
    }
    
    public void initialize(Stage primaryStage) {
    		sceneManager = SceneManager.getInstance(primaryStage);
    		stateManager = StateManager.getInstance();
    		primaryStage.show();
    }

	@FXML
	public void process(ActionEvent event) {
		stateManager.getCurrentState().process(event);
	}

	@FXML public void processNumpad(ActionEvent event) {process(event);}

	@FXML public void processOperator() {}

	@FXML public void switchScene() {}
}
