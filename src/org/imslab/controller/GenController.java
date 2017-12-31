package org.imslab.controller;

import org.imslab.Model;
import org.imslab.scene.SceneManager;
import org.imslab.sqlite.DB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GenController extends Controller {
	
	private Model model;
	
	public GenController() {
		super();
		model = Model.getInstance();
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
		model.setCurrentModQuestionTable(DB.CHINESE_TABLENAME);
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
