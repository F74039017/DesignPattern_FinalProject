package org.imslab.controller;

import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;

public class AddController extends Controller
{

	public AddController() {
		super();
		System.out.println("Create AddController");
	}
	
	public AddController(String name) {
		super(name);
	}

	@FXML 
	public void processAdd() {
		//sql insert
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	
	@FXML 
	public void processCancel() {
		SceneManager.getInstance().switchScene("ModifyDB");
	}
}
