package org.imslab.controller;

import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;

public class EditController extends Controller
{
	public EditController() {
		super();
		System.out.println("Create EditController");
	}
	
	public EditController(String name) {
		super(name);
	}

	@FXML 
	public void processEdit() {
		//sql update
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	
	@FXML 
	public void processCancel() {
		SceneManager.getInstance().switchScene("ModifyDB");
	}
}
