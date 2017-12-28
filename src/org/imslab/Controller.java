package org.imslab;

import org.imslab.scene.SceneManager;
import org.imslab.state.StateManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
	
	private StateManager StateManager;

    @FXML
    private Text output;

    private long number1 = 0;
    private String operator = "";
    private boolean start = true;

    private Model model = new Model();
    
    public Controller() {
		// TODO Auto-generated constructor stub
	}
    
    public Controller(SceneManager sceneManager) {
    		// Wrap sceneManager in the stateManager.
    		StateManager = new StateManager(sceneManager);
    }

    @FXML
    private void processNumpad(ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }

        String value = ((Button)event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();

        if (!"=".equals(value)) {
            if (!operator.isEmpty())
                return;

            operator = value;
            number1 = Long.parseLong(output.getText());
            output.setText("");
        }
        else {
            if (operator.isEmpty())
                return;

            output.setText(String.valueOf(model.calculate(number1, Long.parseLong(output.getText()), operator)));
            operator = "";
            start = true;
        }
    }

	@FXML public void switchScene() {
//		sceneManager.switchScene("ui2");
	}
}
