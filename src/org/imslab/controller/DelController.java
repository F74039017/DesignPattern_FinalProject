package org.imslab.controller;

import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;

public class DelController extends Controller
{
	public DelController() {
		super();
		System.out.println("Create DelController");
	}
	
	public DelController(String name) {
		super(name);
	}

	@FXML 
	public void processDel() {
		//sql delete
		SceneManager.getInstance().switchScene("ModifyDB");
	}
	
	@FXML 
	public void processCancel() {
		SceneManager.getInstance().switchScene("ModifyDB");
	}
}
