package org.imslab.controller;

import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;

public class RegisterController extends Controller
{

	public RegisterController() {
		super();
		System.out.println("Create RegisterController");
	}
	
	public RegisterController(String name) {
		super(name);
	}

	@FXML 
	public void processRegister() {
		//sql insert account
		SceneManager.getInstance().switchScene("Generator");
	}
	
	@FXML 
	public void processBack() {
		SceneManager.getInstance().switchScene("Generator");
	}
}