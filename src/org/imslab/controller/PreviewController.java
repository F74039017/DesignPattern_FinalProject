package org.imslab.controller;

import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;

public class PreviewController extends Controller
{

	public PreviewController() {
		super();
		System.out.println("Create PreviewController");
	}
	
	public PreviewController(String name) {
		super(name);
	}

	@FXML 
	public void processGenerate() {
		SceneManager.getInstance().switchScene("Generator");
		//generate sheet 
	}
	
	@FXML 
	public void processBack() {
		SceneManager.getInstance().switchScene("Generator");
	}
}
