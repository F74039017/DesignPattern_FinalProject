package org.imslab;

import org.imslab.scene.SceneManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController extends Controller {
	
	public LoginController() {
		super();
		System.out.println("Create LoginController");
	}
	
	public LoginController(String name) {
		super(name);
	}

	@FXML public void processNumpad() {
		System.out.println("Process number in LoginController");
	}

	@FXML public void processOperator() {
		SceneManager.getInstance().switchScene("ui2");
	}

	
}
