package org.imslab.controller;

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

	@FXML 
	public void processLogin() {
		//sql驗證
		SceneManager.getInstance().switchScene("Generator");
	}

	
}
