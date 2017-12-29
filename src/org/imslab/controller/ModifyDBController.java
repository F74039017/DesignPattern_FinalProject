package org.imslab.controller;

import org.imslab.scene.SceneManager;

import javafx.fxml.FXML;

public class ModifyDBController extends Controller
{

	public ModifyDBController() {
		super();
		System.out.println("Create ModifyDBController");
	}
	
	public ModifyDBController(String name) {
		super(name);
	}

	@FXML 
	public void processAdd() {
		//sql insert
		SceneManager.getInstance().switchScene("AddProblem");
	}
	
	@FXML 
	public void processEdit() {
		//sql update
		SceneManager.getInstance().switchScene("EditProblem");
	}
	
	@FXML 
	public void processDel() {
		//sql delete
		SceneManager.getInstance().switchScene("DeleteProblem");
	}
	
	@FXML 
	public void processLogout() {
		SceneManager.getInstance().switchScene("Login");
	}
	
	@FXML 
	public void processBack() {
		SceneManager.getInstance().switchScene("Generator");
	}

	
}