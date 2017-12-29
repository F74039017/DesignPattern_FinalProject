package org.imslab.controller;

import org.imslab.scene.SceneManager;

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

	@FXML 
	public void processPreview() {
		SceneManager.getInstance().switchScene("SheetPreview");
	}
	@FXML 
	public void processModifyDB() {
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	@FXML 
	public void processRegister() {
		SceneManager.getInstance().switchScene("Register");
	}
	@FXML 
	public void processLogout() {
		SceneManager.getInstance().switchScene("Login");
	}
	
	
}
