package org.imslab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GenController extends Controller {
	
	public GenController() {
		super();
		System.out.println("Create GenController");
	}
	
	public GenController(String name) {
		super(name);
	}

	@FXML public void processNumpad() {
		System.out.println("Process number in GenController");
	}

	@FXML public void processOperator() {}
}
